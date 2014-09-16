package com.kongur.monolith.weixin.core.message.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.common.utils.XmlTools;
import com.kongur.monolith.weixin.core.message.domain.DefaultMessage;
import com.kongur.monolith.weixin.core.message.domain.EnumEventType;
import com.kongur.monolith.weixin.core.message.domain.EnumMessageType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.event.ClickEventMessage;
import com.kongur.monolith.weixin.core.message.domain.event.LocationEventMessage;
import com.kongur.monolith.weixin.core.message.domain.event.ScanQRCodeEventMessage;
import com.kongur.monolith.weixin.core.message.domain.event.SubscribeEventMessage;
import com.kongur.monolith.weixin.core.message.domain.event.UnsubscribeEventMessage;
import com.kongur.monolith.weixin.core.message.domain.event.ViewEventMessage;
import com.kongur.monolith.weixin.core.message.domain.features.Features;
import com.kongur.monolith.weixin.core.message.domain.normal.ImageMessage;
import com.kongur.monolith.weixin.core.message.domain.normal.LinkMessage;
import com.kongur.monolith.weixin.core.message.domain.normal.LocationMessage;
import com.kongur.monolith.weixin.core.message.domain.normal.TextMessage;
import com.kongur.monolith.weixin.core.message.domain.normal.VideoMessage;
import com.kongur.monolith.weixin.core.message.domain.normal.VoiceMessage;
import com.kongur.monolith.weixin.core.message.domain.voice.VoiceRecognitionMessage;

/**
 * ����΢����Ϣ����
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Service("defaultMessageBuilder")
public class DefaultMessageBuilder implements MessageBuilder {

    private final Logger log = Logger.getLogger(getClass());

    @Override
    public Message<Features> build(HttpServletRequest req) {

        String signature = req.getParameter("signature"); // ǩ��
        String timestamp = req.getParameter("timestamp"); // ʱ���
        String nonce = req.getParameter("nonce"); // �����
        String echostr = req.getParameter("echostr");// ����ַ���
        String appId = req.getParameter("appId");
        return this.build(appId, signature, timestamp, nonce, echostr, req);
    }

    private String readMsg(HttpServletRequest req) {
        String receivedMsg = null;

        int len = req.getContentLength();

        if (len <= 0) {
            log.error("can not find any content in the request. len=" + len);
            return null;
        }

        try {

            InputStream in = req.getInputStream();
            byte[] dataBytes = new byte[len];
            in.read(dataBytes);

            receivedMsg = new String(dataBytes);
            if (log.isDebugEnabled()) {
                log.debug("received message->" + receivedMsg + "<-");
            }

        } catch (IOException e) {
            log.error("read receivedMsg error, req=" + req, e);
            return null;
        }

        return receivedMsg;

    }

    @Override
    public Message<Features> build(String appId, String signature, String timestamp, String nonce, String echostr,
                                   HttpServletRequest req) {
        Message msg = Message.NULL_MESSAGE;

        String receivedMsg = readMsg(req);// ���յ�����Ϣ
        if (StringUtil.isBlank(receivedMsg)) {
            log.error("can not read receivedMsg from the request. req=" + req);
            return msg;
        }

        Map<String, Object> params = null;
        try {
            params = XmlTools.toMap(receivedMsg);
        } catch (DocumentException e) {
            log.error("xml datas convert to Map error, receivedMsg=" + receivedMsg, e);
            return msg;
        }

        // ��Ϣ����
        String msgType = (String) params.get("MsgType");

        if (EnumMessageType.isText(msgType)) {
            msg = new TextMessage(appId, signature, timestamp, nonce, params);
        } else if (EnumMessageType.isImage(msgType)) {
            msg = new ImageMessage(appId, signature, timestamp, nonce, params);
        } else if (EnumMessageType.isVoice(msgType)) {
            // normal voice or Voice Recognition
            String recognition = (String) params.get("Recognition"); // ����ʶ����
            if (recognition != null) {
                msg = new VoiceRecognitionMessage(appId, signature, timestamp, nonce, params);
            } else {
                msg = new VoiceMessage(appId, signature, timestamp, nonce, params);
            }

        } else if (EnumMessageType.isVideo(msgType)) {
            msg = new VideoMessage(appId, signature, timestamp, nonce, params);
        } else if (EnumMessageType.isLocation(msgType)) {
            msg = new LocationMessage(appId, signature, timestamp, nonce, params);
        } else if (EnumMessageType.isLink(msgType)) {
            msg = new LinkMessage(appId, signature, timestamp, nonce, params);
        } else if (EnumMessageType.isEvent(msgType)) {

            // �¼�����
            String eventType = (String) params.get("Event");
            if (EnumEventType.isSubscribe(eventType)) {

                String ticket = (String) params.get("Ticket"); // ��ά���ticket����������ȡ��ά��ͼƬ
                if (ticket != null) {
                    msg = new ScanQRCodeEventMessage(appId, signature, timestamp, nonce, params);
                } else {
                    msg = new SubscribeEventMessage(appId, signature, timestamp, nonce, params);
                }

            } else if (EnumEventType.isUnSubscribe(eventType)) {
                msg = new UnsubscribeEventMessage(appId, signature, timestamp, nonce, params);
            } else if (EnumEventType.isLocation(eventType)) {
                msg = new LocationEventMessage(appId, signature, timestamp, nonce, params);
            } else if (EnumEventType.isClick(eventType)) {
                msg = new ClickEventMessage(appId, signature, timestamp, nonce, params);
            } else if (EnumEventType.isView(eventType)) {
                msg = new ViewEventMessage(appId, signature, timestamp, nonce, params);
            }

        } else {
            msg = new DefaultMessage(appId, signature, timestamp, nonce, params);
        }

        if (msg.isValid()) {
            if (log.isDebugEnabled()) {
                log.debug("build message successfully, message=" + msg);
            }
        }

        return msg;
    }

}

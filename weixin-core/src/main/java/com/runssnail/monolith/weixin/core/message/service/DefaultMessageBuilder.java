package com.runssnail.monolith.weixin.core.message.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.common.utils.HttpRequestUtils;
import com.runssnail.monolith.weixin.core.common.utils.XmlTools;
import com.runssnail.monolith.weixin.core.message.domain.DefaultMessage;
import com.runssnail.monolith.weixin.core.message.domain.EnumEventType;
import com.runssnail.monolith.weixin.core.message.domain.EnumMessageType;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.message.domain.event.ClickEventMessage;
import com.runssnail.monolith.weixin.core.message.domain.event.LocationEventMessage;
import com.runssnail.monolith.weixin.core.message.domain.event.ScanQRCodeEventMessage;
import com.runssnail.monolith.weixin.core.message.domain.event.SubscribeEventMessage;
import com.runssnail.monolith.weixin.core.message.domain.event.UnsubscribeEventMessage;
import com.runssnail.monolith.weixin.core.message.domain.event.ViewEventMessage;
import com.runssnail.monolith.weixin.core.message.domain.features.Features;
import com.runssnail.monolith.weixin.core.message.domain.normal.ImageMessage;
import com.runssnail.monolith.weixin.core.message.domain.normal.LinkMessage;
import com.runssnail.monolith.weixin.core.message.domain.normal.LocationMessage;
import com.runssnail.monolith.weixin.core.message.domain.normal.TextMessage;
import com.runssnail.monolith.weixin.core.message.domain.normal.VideoMessage;
import com.runssnail.monolith.weixin.core.message.domain.normal.VoiceMessage;
import com.runssnail.monolith.weixin.core.message.domain.voice.VoiceRecognitionMessage;

/**
 * 创建微信消息对象
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Service("defaultMessageBuilder")
public class DefaultMessageBuilder implements MessageBuilder {

    private final Logger        log = Logger.getLogger(getClass());

    @Autowired
    private MessageCryptoService messageCryptoService;

    @Override
    public Message<Features> build(HttpServletRequest req) {

        String signature = req.getParameter("signature"); // 签名
        String timestamp = req.getParameter("timestamp"); // 时间戳
        String nonce = req.getParameter("nonce"); // 随机数
        String echostr = req.getParameter("echostr");// 随机字符串
        String appId = req.getParameter("appId");
        return this.build(appId, signature, timestamp, nonce, echostr, req);
    }

    @Override
    public Message<Features> build(String appId, String signature, String timestamp, String nonce, String echostr,
                                   HttpServletRequest req) {
        Message msg = Message.NULL_MESSAGE;

        String receivedMsg = HttpRequestUtils.readMsg(req);// 接收到的消息
        if (StringUtil.isBlank(receivedMsg)) {
            log.error("can not read receivedMsg from the request. req=" + req);
            return msg;
        }

        String encryptType = req.getParameter("encrypt_type");
        String msgSignature = req.getParameter("msg_signature");
        String decryptedMsg = receivedMsg;

        try {
            decryptedMsg = messageCryptoService.decryptMsg(appId, encryptType, msgSignature, timestamp, nonce, receivedMsg);
        } catch (AesException e) {
            log.error("decryptMsg error", e);
            return msg;
        }

        Map<String, Object> params = null;
        try {
            params = XmlTools.toMap(decryptedMsg);
        } catch (DocumentException e) {
            log.error("xml datas convert to Map error, decryptedMsg=" + decryptedMsg, e);
            return msg;
        }

        // 消息类型
        String msgType = (String) params.get("MsgType");

        if (EnumMessageType.isText(msgType)) {
            msg = new TextMessage(appId, signature, timestamp, nonce, params);
        } else if (EnumMessageType.isImage(msgType)) {
            msg = new ImageMessage(appId, signature, timestamp, nonce, params);
        } else if (EnumMessageType.isVoice(msgType)) {
            // normal voice or Voice Recognition
            String recognition = (String) params.get("Recognition"); // 语音识别结果
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

            // 事件类型
            String eventType = (String) params.get("Event");
            if (EnumEventType.isSubscribe(eventType)) {

                String ticket = (String) params.get("Ticket"); // 二维码的ticket，可用来换取二维码图片
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

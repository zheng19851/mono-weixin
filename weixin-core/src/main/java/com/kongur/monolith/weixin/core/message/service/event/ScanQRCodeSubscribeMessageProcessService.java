package com.kongur.monolith.weixin.core.message.service.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.kongur.monolith.weixin.core.message.domain.EnumEventType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.event.ScanQRCodeEventMessage;
import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.domain.SubscribeReplyDO;
import com.kongur.monolith.weixin.core.reply.manager.SubscribeReplyManager;

/**
 * 扫描带参数二维码事件处理服务，用户尚未关注
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
// @Service("scanQRCodeSubscribeMessageProcessService")
public class ScanQRCodeSubscribeMessageProcessService extends AbstractEventMessageProcessService<ScanQRCodeEventMessage> {

    @Autowired
    private SubscribeReplyManager subscribeReplyManager;

    @Override
    public boolean supports(Message msg) {
        if (!super.supports(msg)) {
            return false;
        }

        if (!(msg instanceof ScanQRCodeEventMessage)) {
            return false;
        }

        ScanQRCodeEventMessage smsg = (ScanQRCodeEventMessage) msg;

        return EnumEventType.isSubscribe(smsg.getEventType()) && smsg.getTicket() != null;
    }

    @Override
    protected Reply buildReply(ScanQRCodeEventMessage msg) {
        SubscribeReplyDO reply = subscribeReplyManager.getSubscribeReply();

        return reply;
    }

}

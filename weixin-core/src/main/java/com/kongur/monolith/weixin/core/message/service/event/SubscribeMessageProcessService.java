package com.kongur.monolith.weixin.core.message.service.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.kongur.monolith.weixin.core.message.domain.EnumEventType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.event.SubscribeEventMessage;
import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.domain.SubscribeReplyDO;
import com.kongur.monolith.weixin.core.reply.manager.SubscribeReplyManager;

/**
 * 关注事件处理服务
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
// @Service("subscribeMessageProcessService")
public class SubscribeMessageProcessService extends AbstractEventMessageProcessService<SubscribeEventMessage> {

    @Autowired
    private SubscribeReplyManager subscribeReplyManager;

    @Override
    public boolean supports(Message msg) {
        if (!super.supports(msg)) {
            return false;
        }

        if (!(msg instanceof SubscribeEventMessage)) {
            return false;
        }

        SubscribeEventMessage sub = (SubscribeEventMessage) msg;

        return EnumEventType.isSubscribe(sub.getEventType());
    }

    @Override
    protected Reply buildReply(SubscribeEventMessage msg) {
        SubscribeReplyDO reply = subscribeReplyManager.getSubscribeReply(msg.getAppId());

        return reply;
    }

}

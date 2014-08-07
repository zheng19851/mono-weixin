package com.kongur.monolith.weixin.core.service.message.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.kongur.monolith.weixin.common.domain.dto.Reply;
import com.kongur.monolith.weixin.core.domain.SubscribeReplyDO;
import com.kongur.monolith.weixin.core.domain.message.EnumEventType;
import com.kongur.monolith.weixin.core.domain.message.Message;
import com.kongur.monolith.weixin.core.domain.message.event.SubscribeEventMessage;
import com.kongur.monolith.weixin.core.manager.SubscribeReplyManager;

/**
 * ��ע�¼��������
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
        SubscribeReplyDO reply = subscribeReplyManager.getSubscribeReply();

        return reply;
    }

}

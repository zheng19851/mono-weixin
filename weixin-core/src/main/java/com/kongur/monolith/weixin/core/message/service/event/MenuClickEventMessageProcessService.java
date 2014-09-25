package com.kongur.monolith.weixin.core.message.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.kongur.monolith.weixin.core.menu.service.MenuManager;
import com.kongur.monolith.weixin.core.message.domain.EnumEventType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.event.ClickEventMessage;
import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;

/**
 * �˵������¼��������
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
@Service("menuClickEventMessageProcessService")
@Order(27)
public class MenuClickEventMessageProcessService extends AbstractEventMessageProcessService<ClickEventMessage> {

    @Autowired
    private MenuManager menuManager;

    @Override
    public boolean supports(Message msg) {
        if (!super.supports(msg)) {
            return false;
        }

        if (!(msg instanceof ClickEventMessage)) {
            return false;
        }

        ClickEventMessage click = (ClickEventMessage) msg;

        return EnumEventType.isClick(click.getEventType());
    }

    @Override
    protected Reply buildReply(ClickEventMessage msg) {
        ReplyDO reply = menuManager.getReplyByEventKey(msg.getEventKey());
        return reply;
    }

}

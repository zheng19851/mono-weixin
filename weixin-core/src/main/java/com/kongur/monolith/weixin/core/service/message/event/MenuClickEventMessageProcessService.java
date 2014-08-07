package com.kongur.monolith.weixin.core.service.message.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.kongur.monolith.weixin.common.domain.dto.Reply;
import com.kongur.monolith.weixin.core.domain.ReplyDO;
import com.kongur.monolith.weixin.core.domain.message.EnumEventType;
import com.kongur.monolith.weixin.core.domain.message.Message;
import com.kongur.monolith.weixin.core.domain.message.event.ClickEventMessage;
import com.kongur.monolith.weixin.core.manager.MenuManager;

/**
 * �˵������¼��������
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
// @Service("menuClickEventMessageProcessService")
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

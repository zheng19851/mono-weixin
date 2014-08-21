package com.kongur.monolith.weixin.core.message.service.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.kongur.monolith.weixin.core.menu.service.MenuManager;
import com.kongur.monolith.weixin.core.message.domain.EnumEventType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.event.ClickEventMessage;
import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;

/**
 * 菜单单击事件处理服务
 * 
 * @author zhengwei
 * @date 2014年2月21日
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

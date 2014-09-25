package com.kongur.monolith.weixin.core.reply.service.unactive;

import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.service.VelocityReplyMessageBuilder;

/**
 * 被动发送回复
 * 
 * @author zhengwei
 * @date 2014年2月21日
 */
public abstract class UnactiveVelocityReplyMessageBuilder<R extends Reply> extends VelocityReplyMessageBuilder<R> {

    @Override
    public boolean supports(Reply reply) {
        return !reply.isActive();
    }

}

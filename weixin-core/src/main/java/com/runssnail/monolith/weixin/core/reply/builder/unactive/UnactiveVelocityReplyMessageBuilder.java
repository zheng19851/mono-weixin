package com.runssnail.monolith.weixin.core.reply.builder.unactive;

import com.runssnail.monolith.weixin.core.reply.builder.VelocityReplyMessageBuilder;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

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

package com.runssnail.monolith.weixin.core.reply.builder.unactive;

import com.runssnail.monolith.weixin.core.reply.builder.VelocityReplyMessageBuilder;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * �������ͻظ�
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public abstract class UnactiveVelocityReplyMessageBuilder<R extends Reply> extends VelocityReplyMessageBuilder<R> {

    @Override
    public boolean supports(Reply reply) {
        return !reply.isActive();
    }

}

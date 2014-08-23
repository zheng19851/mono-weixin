package com.kongur.monolith.weixin.core.reply.service.unactive;

import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.service.VelocityReplyMessageBuilder;

/**
 * �������ͻظ�
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public abstract class UnactiveVelocityReplyMessageBuilder<R extends Reply> extends VelocityReplyMessageBuilder<R> {

    @Override
    public boolean supports(R reply) {
        return !reply.isActive();
    }

}
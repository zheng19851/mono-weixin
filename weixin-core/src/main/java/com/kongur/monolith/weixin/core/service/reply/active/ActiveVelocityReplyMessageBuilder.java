package com.kongur.monolith.weixin.core.service.reply.active;

import com.kongur.monolith.weixin.common.domain.dto.Reply;
import com.kongur.monolith.weixin.core.service.reply.VelocityReplyMessageBuilder;

/**
 * �������ͻظ�
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public abstract class ActiveVelocityReplyMessageBuilder<R extends Reply> extends VelocityReplyMessageBuilder<R> {

    @Override
    public boolean supports(R reply) {
        return reply.isActive();
    }

}

package com.kongur.monolith.weixin.core.service.reply.unactive;

import com.kongur.monolith.weixin.common.domain.dto.Reply;

/**
 * ��Դ����
 * 
 * @author zhengwei
 * @date 2014��2��26��
 */
public abstract class UnactiveResourceReplyMessageBuilder<R extends Reply> extends UnactiveVelocityReplyMessageBuilder<R> {

    @Override
    public boolean supports(R reply) {
        return super.supports(reply) && reply.isResource();
    }

}

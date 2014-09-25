package com.kongur.monolith.weixin.core.reply.service.unactive;

import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * ��Դ����
 * 
 * @author zhengwei
 * @date 2014��2��26��
 */
public abstract class UnactiveResourceReplyMessageBuilder<R extends Reply> extends UnactiveVelocityReplyMessageBuilder<R> {

    @Override
    public boolean supports(Reply reply) {
        return super.supports(reply) && reply.isResource();
    }

}

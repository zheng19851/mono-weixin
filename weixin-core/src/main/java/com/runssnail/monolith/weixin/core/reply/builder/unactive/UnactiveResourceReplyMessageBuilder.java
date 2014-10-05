package com.runssnail.monolith.weixin.core.reply.builder.unactive;

import com.runssnail.monolith.weixin.core.reply.domain.Reply;

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

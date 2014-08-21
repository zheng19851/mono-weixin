package com.kongur.monolith.weixin.core.reply.service;

import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * ���ҷ���Ҫ��Ĵ������
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public interface ReplyMessageBuilderResolver<R extends Reply> {

    /**
     * ����
     * 
     * @param reply
     * @return
     */
    ReplyMessageBuilder<R> resolve(R reply);

}

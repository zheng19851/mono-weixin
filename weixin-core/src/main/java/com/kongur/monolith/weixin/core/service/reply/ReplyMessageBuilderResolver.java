package com.kongur.monolith.weixin.core.service.reply;

import com.kongur.monolith.weixin.common.domain.dto.Reply;

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

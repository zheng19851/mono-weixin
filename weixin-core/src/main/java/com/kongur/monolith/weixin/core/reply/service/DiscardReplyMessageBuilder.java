package com.kongur.monolith.weixin.core.reply.service;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * ���ظ��κ���Ϣ
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
@Service("discardReplyMessageBuilder")
@Order(Ordered.LOWEST_PRECEDENCE)
public class DiscardReplyMessageBuilder extends AbstractReplyMessageBuilder<Reply> {

    @Override
    public boolean supports(Reply reply) {
        return true;
    }

    @Override
    protected void doBuild(Reply reply, Message msg, Result<String> result) {

        log.warn("the reply is discarded. reply=" + reply);

    }

}

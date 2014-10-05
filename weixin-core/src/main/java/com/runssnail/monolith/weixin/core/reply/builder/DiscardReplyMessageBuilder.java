package com.runssnail.monolith.weixin.core.reply.builder;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * 不回复任何消息
 * 
 * @author zhengwei
 * @date 2014年2月21日
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

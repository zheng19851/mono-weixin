package com.kongur.monolith.weixin.core.custom.builder;

import java.util.Map;

import com.kongur.monolith.weixin.core.custom.domain.CustomReply;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.service.VelocityReplyMessageBuilder;

/**
 * 客服消息回复抽象接口
 * 
 * @author zhengwei
 *
 * @param <R>
 */
public abstract class CustomVelocityReplyMessageBuilder<R extends CustomReply> extends VelocityReplyMessageBuilder<R> {

    @Override
    public boolean supports(Reply reply) {
        return (reply instanceof CustomReply);
    }

    @Override
    protected void buildModelParams(R reply, Message msg, Map model) {
        
    }

}

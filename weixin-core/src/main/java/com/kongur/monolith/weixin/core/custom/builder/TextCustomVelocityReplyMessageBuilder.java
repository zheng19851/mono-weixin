package com.kongur.monolith.weixin.core.custom.builder;

import org.springframework.util.Assert;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.custom.message.TextCustomMessage;
import com.kongur.monolith.weixin.core.custom.CustomReply;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * 客服文本消息内容创建接口
 * 
 * @author zhengwei
 */
public class TextCustomVelocityReplyMessageBuilder extends CustomVelocityReplyMessageBuilder<CustomReply<TextCustomMessage>> {

    @Override
    public boolean supports(Reply reply) {
        return super.supports(reply) && reply.isText();
    }

    @Override
    protected void validate(CustomReply<TextCustomMessage> reply, Message msg, Result<String> result) {
        Assert.notNull(reply.getCustomMessage());
        Assert.notNull(reply.getCustomMessage().getAppId(), "appId 不能为null");
        Assert.notNull(reply.getCustomMessage().getMsgType(), "msgType 不能为null");
        Assert.notNull(reply.getCustomMessage().getContent(), "content 不能为null");
    }

}

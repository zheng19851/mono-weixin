package com.kongur.monolith.weixin.core.reply.builder.unactive;

import java.util.Map;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.common.EnumMessageType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;

/**
 * 图文消息回复内容组装器
 * 
 * @author zhengwei
 * @date 2014年2月26日
 */
//@Service("newsReplyMessageBuilder")
public class NewsReplyMessageBuilder extends UnactiveVelocityReplyMessageBuilder<ReplyDO> {

    @Override
    public boolean supports(Reply reply) {
        return super.supports(reply) && EnumMessageType.isNews(reply.getType());
    }

    @Override
    protected void validate(ReplyDO reply, Message msg, Result<String> result) {

        if (!reply.hasItems()) {
            result.setError("3001", "至少设置1个图文消息");
            return;
        }

    }

    @Override
    protected void buildModelParams(ReplyDO reply, Message msg, Map model) {
        // ignore
    }

}

package com.runssnail.monolith.weixin.core.custom.builder;

import com.runssnail.monolith.weixin.client.common.EnumMessageType;
import com.runssnail.monolith.weixin.client.custom.message.NewsCustomMessage;
import com.runssnail.monolith.weixin.core.custom.CustomReply;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;


/**
 * Í¼ÎÄÏûÏ¢
 * 
 * @author zhengwei
 *
 */
public class NewsCustomVelocityReplyMessageBuilder extends CustomVelocityReplyMessageBuilder<CustomReply<NewsCustomMessage>> {

    @Override
    public boolean supports(Reply reply) {
        return super.supports(reply) && EnumMessageType.isNews(reply.getType());
    }

}

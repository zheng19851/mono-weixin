package com.runssnail.monolith.weixin.core.template;

import java.util.Map;

import javax.annotation.Resource;

import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.reply.builder.VelocityReplyMessageBuilder;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;
import com.runssnail.monolith.weixin.core.web.url.URLBroker;

/**
 * 模板消息数据生成器
 * 
 * @author zhengwei
 */
public class TemplateVelocityReplyMessageBuilder extends VelocityReplyMessageBuilder<TemplateMessageReply> {

    @Resource(name = "appServerBroker")
    private URLBroker appServerBroker;

    @Override
    public boolean supports(Reply reply) {
        return (reply instanceof TemplateMessageReply);
    }

    @Override
    protected void buildModelParams(TemplateMessageReply reply, Message msg, Map model) {
        model.put("appServer", this.appServerBroker.toString());
    }

}

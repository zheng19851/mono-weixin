package com.kongur.monolith.weixin.core.template;

import java.util.Map;

import javax.annotation.Resource;

import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.builder.VelocityReplyMessageBuilder;
import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.web.url.URLBroker;

/**
 * ģ����Ϣ����������
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

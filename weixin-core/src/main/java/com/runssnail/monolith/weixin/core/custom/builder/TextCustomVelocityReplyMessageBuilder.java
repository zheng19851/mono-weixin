package com.runssnail.monolith.weixin.core.custom.builder;

import org.springframework.util.Assert;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.custom.message.TextCustomMessage;
import com.runssnail.monolith.weixin.core.custom.CustomReply;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * �ͷ��ı���Ϣ���ݴ����ӿ�
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
        Assert.notNull(reply.getCustomMessage().getAppId(), "appId ����Ϊnull");
        Assert.notNull(reply.getCustomMessage().getMsgType(), "msgType ����Ϊnull");
        Assert.notNull(reply.getCustomMessage().getContent(), "content ����Ϊnull");
    }

}

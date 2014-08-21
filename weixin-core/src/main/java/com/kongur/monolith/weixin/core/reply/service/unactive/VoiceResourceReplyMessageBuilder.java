package com.kongur.monolith.weixin.core.reply.service.unactive;

import java.util.Map;

import com.kongur.monolith.weixin.client.common.EnumMessageType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * 语音消息类型
 * 
 * @author zhengwei
 * @date 2014年2月26日
 */
public class VoiceResourceReplyMessageBuilder extends UnactiveResourceReplyMessageBuilder<Reply> {

    @Override
    public boolean supports(Reply reply) {
        return super.supports(reply) && EnumMessageType.isVoice(reply.getType());
    }

    @Override
    protected void buildModelParams(Reply reply, Message msg, Map model) {
        // model.put("toUser", msg.getFromUserName());
        // model.put("fromUser", msg.getToUserName());
        //
        // model.put("createTime", new Date().getTime());
        // // model.put("msgType", msg.getMsgType());
        //
        // model.put("reply", reply);
        // ignore

    }

}

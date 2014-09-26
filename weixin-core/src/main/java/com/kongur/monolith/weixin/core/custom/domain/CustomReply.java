package com.kongur.monolith.weixin.core.custom.domain;

import com.kongur.monolith.weixin.client.common.EnumMessageType;
import com.kongur.monolith.weixin.client.custom.message.CustomMessage;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * 客服消息回复
 * 
 * @author zhengwei
 */
public class CustomReply implements Reply {

    private CustomMessage customMessage;

    public CustomReply(CustomMessage msg) {
        this.customMessage = msg;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isText() {
        return EnumMessageType.isText(customMessage.getMsgType());
    }

    @Override
    public String getType() {
        return customMessage.getMsgType();
    }

    @Override
    public boolean isResource() {
        return EnumMessageType.isImage(getType()) || EnumMessageType.isVoice(getType())
               || EnumMessageType.isVedio(getType()) || EnumMessageType.isMusic(getType());
    }

    public CustomMessage getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(CustomMessage customMessage) {
        this.customMessage = customMessage;
    }

}

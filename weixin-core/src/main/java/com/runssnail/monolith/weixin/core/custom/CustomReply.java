package com.runssnail.monolith.weixin.core.custom;

import com.runssnail.monolith.weixin.client.common.EnumMessageType;
import com.runssnail.monolith.weixin.client.custom.message.CustomMessage;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * 客服消息回复
 * 
 * @author zhengwei
 */
public class CustomReply<M extends CustomMessage> implements Reply {

    private M customMessage;

    public CustomReply(M msg) {
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

    public M getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(M customMessage) {
        this.customMessage = customMessage;
    }

}

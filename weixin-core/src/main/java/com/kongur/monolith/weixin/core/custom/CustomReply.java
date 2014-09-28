package com.kongur.monolith.weixin.core.custom;

import com.kongur.monolith.weixin.client.common.EnumMessageType;
import com.kongur.monolith.weixin.client.custom.message.CustomMessage;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * �ͷ���Ϣ�ظ�
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

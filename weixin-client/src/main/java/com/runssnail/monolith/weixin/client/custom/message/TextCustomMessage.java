package com.runssnail.monolith.weixin.client.custom.message;

import com.runssnail.monolith.weixin.client.common.EnumMessageType;

/**
 * 文本消息
 * 
 * @author zhengwei
 */
public class TextCustomMessage extends AbstractCustomMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 1742891772732624134L;

    /**
     * 内容
     */
    private String            content;

    public TextCustomMessage() {
        super(EnumMessageType.TEXT.getValue(), null, null);
    }

    public TextCustomMessage(String appId, String toUser, String content) {
        super(EnumMessageType.TEXT.getValue(), appId, toUser);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

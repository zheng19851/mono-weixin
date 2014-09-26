package com.kongur.monolith.weixin.client.custom.message;

import com.kongur.monolith.weixin.client.common.EnumMessageType;

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

    public TextCustomMessage() {
        super(EnumMessageType.TEXT.getValue());
    }

    /**
     * 内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

package com.kongur.monolith.weixin.client.domain.dto.custom;

/**
 * 文本消息
 * 
 * @author zhengwei
 */
public class TextCustomMessage extends AbstractCustomMessage {

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

package com.kongur.monolith.weixin.client.custom.message;

import com.kongur.monolith.weixin.client.common.EnumMessageType;

/**
 * �ı���Ϣ
 * 
 * @author zhengwei
 */
public class TextCustomMessage extends AbstractCustomMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 1742891772732624134L;

    /**
     * ����
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

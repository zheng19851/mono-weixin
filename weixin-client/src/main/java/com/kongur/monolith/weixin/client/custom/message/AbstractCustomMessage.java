package com.kongur.monolith.weixin.client.custom.message;

/**
 * 客户消息对象
 * 
 * @author zhengwei
 */
public abstract class AbstractCustomMessage implements CustomMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 6860325594955423864L;

    private String msgType;

    private String toUser;

    public AbstractCustomMessage(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String getMsgType() {
        return this.msgType;
    }

    @Override
    public String getToUser() {
        return this.toUser;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

}

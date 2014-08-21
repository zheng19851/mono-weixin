package com.kongur.monolith.weixin.common.domain.dto.custom;

/**
 * �ͻ���Ϣ����
 * 
 * @author zhengwei
 */
public abstract class AbstractCustomMessage implements CustomMessage {

    private String msgType;

    private String toUser;

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

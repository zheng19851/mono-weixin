package com.kongur.monolith.weixin.client.custom.message;

/**
 * 客户消息对象
 * 
 * @author zhengwei
 */
public interface CustomMessage {

    /**
     * 消息类型
     * 
     * @return
     */
    String getMsgType();

    /**
     * 收消息人
     * 
     * @return
     */
    String getToUser();

}

package com.kongur.monolith.weixin.common.domain.dto.custom;

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

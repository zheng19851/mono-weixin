package com.runssnail.monolith.weixin.client.custom.message;

import java.io.Serializable;

/**
 * 客户消息对象
 * 
 * @author zhengwei
 */
public interface CustomMessage extends Serializable {
    
    /**
     * 微信公众号appid
     * 
     * @return
     */
    String getAppId();

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

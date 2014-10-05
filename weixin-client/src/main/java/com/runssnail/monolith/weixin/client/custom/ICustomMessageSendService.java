package com.runssnail.monolith.weixin.client.custom;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.custom.message.CustomMessage;

/**
 * 向订阅的用户发送消息
 * 
 * @author zhengwei
 */
public interface ICustomMessageSendService {

    /**
     * 发送
     * 
     * @param msg
     * @return
     */
    Result<Object> send(CustomMessage msg);

}

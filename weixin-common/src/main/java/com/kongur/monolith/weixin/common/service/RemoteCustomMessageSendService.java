package com.kongur.monolith.weixin.common.service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.custom.CustomMessage;

/**
 * 向订阅的用户发送消息
 * 
 * @author zhengwei
 */
public interface RemoteCustomMessageSendService {

    /**
     * 发送
     * 
     * @param msg
     * @return
     */
    Result<Object> send(CustomMessage msg);

}

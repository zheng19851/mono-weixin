package com.kongur.monolith.weixin.common.service;

import com.kongur.monolith.common.result.Result;


/**
 * 外部应用发送事件，通知weixin应用
 * 
 * @author zhengwei
 */
public interface RemoteAppEventService {

    /**
     * 通知
     * 
     * @param obj
     */
    Result<Object> onEvent(Object obj);

}

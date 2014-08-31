package com.kongur.monolith.weixin.client.support;

import com.kongur.monolith.common.result.Result;

/**
 * 外部应用发送事件，通知weixin应用
 * 
 * @author zhengwei
 */
public interface RemoteAppEventService {

    /**
     * 投递事件
     * 
     * @param obj
     */
    Result<Object> multicastEvent(Object event);

}

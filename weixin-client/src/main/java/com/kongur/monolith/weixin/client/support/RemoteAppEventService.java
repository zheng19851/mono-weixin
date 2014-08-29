package com.kongur.monolith.weixin.client.support;

import com.kongur.monolith.common.result.Result;

/**
 * 外部应用发送事件，通知weixin应用
 * 
 * @author zhengwei
 */
public interface RemoteAppEventService {

    /**
     * 添加监听器
     * 
     * @param listener
     */
    void addAppEventListener(AppEventListener listener);

    /**
     * 投递事件
     * 
     * @param obj
     */
    Result<Object> multicastEvent(Object event);

}

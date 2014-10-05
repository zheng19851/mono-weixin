package com.runssnail.monolith.weixin.client.support;

import com.runssnail.monolith.common.result.Result;

/**
 * 外部应用发送事件，通知weixin应用
 * 
 * @author zhengwei
 */
public interface IAppEventService {

    /**
     * 投递事件
     * 
     * @param obj
     */
    Result<Object> multicastEvent(RemoteAppEvent appEvent);

}

package com.runssnail.monolith.weixin.client.support;

import com.runssnail.monolith.common.result.Result;

/**
 * �ⲿӦ�÷����¼���֪ͨweixinӦ��
 * 
 * @author zhengwei
 */
public interface IAppEventService {

    /**
     * Ͷ���¼�
     * 
     * @param obj
     */
    Result<Object> multicastEvent(RemoteAppEvent appEvent);

}

package com.kongur.monolith.weixin.client.support;

import com.kongur.monolith.common.result.Result;

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

package com.kongur.monolith.weixin.client.support;

import com.kongur.monolith.common.result.Result;

/**
 * �ⲿӦ�÷����¼���֪ͨweixinӦ��
 * 
 * @author zhengwei
 */
public interface RemoteAppEventService {

    /**
     * Ͷ���¼�
     * 
     * @param obj
     */
    Result<Object> multicastEvent(Object event);

}

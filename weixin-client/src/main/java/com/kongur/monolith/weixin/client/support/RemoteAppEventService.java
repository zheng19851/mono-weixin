package com.kongur.monolith.weixin.client.support;

import com.kongur.monolith.common.result.Result;

/**
 * �ⲿӦ�÷����¼���֪ͨweixinӦ��
 * 
 * @author zhengwei
 */
public interface RemoteAppEventService {

    /**
     * ��Ӽ�����
     * 
     * @param listener
     */
    void addAppEventListener(AppEventListener listener);

    /**
     * Ͷ���¼�
     * 
     * @param obj
     */
    Result<Object> multicastEvent(Object event);

}

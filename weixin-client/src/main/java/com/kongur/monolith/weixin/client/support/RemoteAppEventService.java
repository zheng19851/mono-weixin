package com.kongur.monolith.weixin.client.support;

import com.kongur.monolith.common.result.Result;


/**
 * �ⲿӦ�÷����¼���֪ͨweixinӦ��
 * 
 * @author zhengwei
 */
public interface RemoteAppEventService {

    /**
     * ֪ͨ
     * 
     * @param obj
     */
    Result<Object> onEvent(Object obj);

}

package com.kongur.monolith.weixin.client.service;

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

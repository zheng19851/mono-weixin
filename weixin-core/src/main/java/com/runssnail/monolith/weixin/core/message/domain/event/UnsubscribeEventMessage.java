package com.runssnail.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.UnsubscribeEventFeatures;

/**
 * ȡ����ע��Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class UnsubscribeEventMessage extends EventMessage<UnsubscribeEventFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = 6931700961230934989L;

    /**
     * ȡ����ע��Ϣ
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public UnsubscribeEventMessage(String appId, String signature, String timestamp, String nonce,
                                   Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);

        UnsubscribeEventFeatures features = new UnsubscribeEventFeatures(getEventType());
        this.setFeatures(features);
    }

}

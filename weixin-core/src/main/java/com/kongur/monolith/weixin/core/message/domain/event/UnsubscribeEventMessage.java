package com.kongur.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.features.UnsubscribeEventFeatures;

/**
 * 取消关注消息
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
     * 取消关注消息
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

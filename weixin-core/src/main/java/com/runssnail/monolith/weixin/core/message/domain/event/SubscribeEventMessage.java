package com.runssnail.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.SubscribeEventFeatures;

/**
 * 关注事件消息
 *
 * @author zhengwei
 * @date 2014-2-19
 */
public class SubscribeEventMessage extends EventMessage<SubscribeEventFeatures> {

    public SubscribeEventMessage(String appId, String signature, String timestamp, String nonce,
                                 Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        SubscribeEventFeatures features = new SubscribeEventFeatures(getEventType());
        this.setFeatures(features);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 2085631136417374320L;

}

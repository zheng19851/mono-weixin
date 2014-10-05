package com.runssnail.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.ViewEventFeatures;

/**
 * 点击菜单跳转链接时的事件推送
 * 
 * @author zhengwei
 */
public class ViewEventMessage extends EventMessage<ViewEventFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ViewEventMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        ViewEventFeatures features = new ViewEventFeatures(getEventType());
        this.setFeatures(features);
    }

}

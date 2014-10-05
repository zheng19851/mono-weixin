package com.runssnail.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.ClickEventFeatures;

/**
 * 自定义菜单事件
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class ClickEventMessage extends EventMessage<ClickEventFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = -4376011937062217577L;

    /**
     * 自定义菜单事件
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public ClickEventMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        this.setFeatures(new ClickEventFeatures(getEventType(), this.getString("EventKey")));
    }

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     * 
     * @return
     */
    public String getEventKey() {
        return this.getFeatures().getEventKey();
    }

}

package com.runssnail.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.AbstractMessage;
import com.runssnail.monolith.weixin.core.message.domain.features.Features;

/**
 * 事件推送的消息
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public class EventMessage<F extends Features> extends AbstractMessage<F> {

    public EventMessage(String signature, String timestamp, String nonce) {
        super(signature, timestamp, nonce);
    }

    public EventMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 910441650557994025L;

    public String getEventType() {
        return this.getString("Event");
    }

}

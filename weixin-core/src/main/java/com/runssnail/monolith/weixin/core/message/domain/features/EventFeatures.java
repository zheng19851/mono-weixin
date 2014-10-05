package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * 事件类型特性
 * 
 * @author zhengwei
 */
public class EventFeatures extends AbstractFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = -8461303162576302330L;
    private String event;

    public EventFeatures(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}

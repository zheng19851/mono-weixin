package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * �˵�����¼�����
 * 
 * @author zhengwei
 */
public class ClickEventFeatures extends EventFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 3912749474949053066L;
    private String eventKey;

    public ClickEventFeatures(String event, String eventKey) {
        super(event);
        this.eventKey = eventKey;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

}

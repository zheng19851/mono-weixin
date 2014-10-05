package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * 扫描带参数二维码事件
 * 
 * @author zhengwei
 */
public class ScanQRCodeEventFeatures extends EventFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 3744889874974187821L;

    private String            eventKey;

    private String            ticket;

    public ScanQRCodeEventFeatures(String event, String eventKey, String ticket) {
        super(event);
        this.eventKey = eventKey;
        this.ticket = ticket;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}

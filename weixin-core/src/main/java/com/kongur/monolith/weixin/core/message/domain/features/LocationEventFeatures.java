package com.kongur.monolith.weixin.core.message.domain.features;

/**
 * �ϱ�����λ���¼�
 * 
 * @author zhengwei
 */
public class LocationEventFeatures extends EventFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 2356029830284558483L;

    /**
     * ����λ��γ��
     */
    private String            atitude;

    /**
     * ����λ�þ���
     */
    private String            longitude;

    /**
     * ����λ�þ���
     */
    private String            precision;

    public LocationEventFeatures(String event, String atitude, String longitude, String precision) {
        super(event);

        this.atitude = atitude;
        this.longitude = longitude;
        this.precision = precision;

    }

    public String getAtitude() {
        return atitude;
    }

    public void setAtitude(String atitude) {
        this.atitude = atitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

}

package com.kongur.monolith.weixin.core.message.domain.features;

/**
 * 上报地理位置事件
 * 
 * @author zhengwei
 */
public class LocationEventFeatures extends EventFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 2356029830284558483L;

    /**
     * 地理位置纬度
     */
    private String            atitude;

    /**
     * 地理位置经度
     */
    private String            longitude;

    /**
     * 地理位置精度
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

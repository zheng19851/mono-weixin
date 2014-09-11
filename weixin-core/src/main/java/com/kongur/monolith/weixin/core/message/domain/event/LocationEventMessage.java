package com.kongur.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.features.LocationEventFeatures;

/**
 * 上报地理位置事件
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class LocationEventMessage extends EventMessage<LocationEventFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = 9175040775006415998L;

    /**
     * 上报地理位置事件
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public LocationEventMessage(String appId, String signature, String timestamp, String nonce,
                                Map<String, Object> params) {
        super(signature, timestamp, nonce, params);

        LocationEventFeatures features = new LocationEventFeatures(getEventType(), getString("Latitude"),
                                                                   getString("Longitude"), getString("Precision"));
        setFeatures(features);
    }

    /**
     * 地理位置纬度
     * 
     * @return
     */
    public String getLatitude() {

        return this.getFeatures().getLongitude();
    }

    /**
     * 地理位置经度
     * 
     * @return
     */
    public String getLongitude() {
        return this.getFeatures().getLongitude();
    }

    /**
     * 地理位置精度
     * 
     * @return
     */
    public String getPrecision() {
        return this.getFeatures().getPrecision();
    }
}

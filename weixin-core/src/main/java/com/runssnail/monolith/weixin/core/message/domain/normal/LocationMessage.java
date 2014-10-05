package com.runssnail.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.LocationFeatures;

/**
 * 地理位置消息
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class LocationMessage extends NormalMessage<LocationFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = 2669328703261957343L;

    /**
     * 地理位置消息
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public LocationMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        LocationFeatures features = new LocationFeatures(getString("Location_X"), getString("Location_Y"),
                                                         super.getInt("Scale"), getString("Label"));
        this.setFeatures(features);
    }

    /**
     * 地理位置维度
     * 
     * @return
     */
    public String getLocationX() {
        return this.getFeatures().getLocationX();
    }

    /**
     * 地理位置经度
     * 
     * @return
     */
    public String getLocationY() {
        return this.getFeatures().getLocationY();
    }

    /**
     * 地图缩放大小
     * 
     * @return
     */
    public int getScale() {
        return this.getFeatures().getScale();
    }

    /**
     * 地理位置信息
     * 
     * @return
     */
    public String getLabel() {
        return this.getFeatures().getLabel();
    }

}

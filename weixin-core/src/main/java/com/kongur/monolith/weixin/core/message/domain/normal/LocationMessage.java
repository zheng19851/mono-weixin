package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.features.LocationFeatures;

/**
 * ����λ����Ϣ
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
     * ����λ����Ϣ
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
     * ����λ��ά��
     * 
     * @return
     */
    public String getLocationX() {
        return this.getFeatures().getLocationX();
    }

    /**
     * ����λ�þ���
     * 
     * @return
     */
    public String getLocationY() {
        return this.getFeatures().getLocationY();
    }

    /**
     * ��ͼ���Ŵ�С
     * 
     * @return
     */
    public int getScale() {
        return this.getFeatures().getScale();
    }

    /**
     * ����λ����Ϣ
     * 
     * @return
     */
    public String getLabel() {
        return this.getFeatures().getLabel();
    }

}

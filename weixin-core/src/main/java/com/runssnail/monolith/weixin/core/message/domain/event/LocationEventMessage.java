package com.runssnail.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.LocationEventFeatures;

/**
 * �ϱ�����λ���¼�
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
     * �ϱ�����λ���¼�
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
     * ����λ��γ��
     * 
     * @return
     */
    public String getLatitude() {

        return this.getFeatures().getLongitude();
    }

    /**
     * ����λ�þ���
     * 
     * @return
     */
    public String getLongitude() {
        return this.getFeatures().getLongitude();
    }

    /**
     * ����λ�þ���
     * 
     * @return
     */
    public String getPrecision() {
        return this.getFeatures().getPrecision();
    }
}

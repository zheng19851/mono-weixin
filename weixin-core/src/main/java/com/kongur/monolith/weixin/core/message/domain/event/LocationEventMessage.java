package com.kongur.monolith.weixin.core.message.domain.event;

import java.util.Map;

/**
 * �ϱ�����λ���¼�
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class LocationEventMessage extends EventMessage {

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
    public LocationEventMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * ����λ��γ��
     * 
     * @return
     */
    public String getLatitude() {

        return this.getString("Latitude");
    }

    /**
     * ����λ�þ���
     * 
     * @return
     */
    public String getLongitude() {
        return this.getString("Longitude");
    }

    /**
     * ����λ�þ���
     * 
     * @return
     */
    public String getPrecision() {
        return this.getString("Precision");
    }
}

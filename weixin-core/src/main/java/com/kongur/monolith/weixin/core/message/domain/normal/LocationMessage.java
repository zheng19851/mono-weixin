package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

/**
 * ����λ����Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class LocationMessage extends NormalMessage {

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
    public LocationMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * ����λ��ά��
     * 
     * @return
     */
    public String getLocationX() {
        return this.getString("Location_X");
    }

    /**
     * ����λ�þ���
     * 
     * @return
     */
    public String getLocationY() {
        return this.getString("Location_Y");
    }

    /**
     * ��ͼ���Ŵ�С
     * 
     * @return
     */
    public int getScale() {

        String scale = this.getString("Scale");

        return Integer.valueOf(scale);
    }

    /**
     * ����λ����Ϣ
     * 
     * @return
     */
    public String getLabel() {
        return this.getString("Label");
    }

}

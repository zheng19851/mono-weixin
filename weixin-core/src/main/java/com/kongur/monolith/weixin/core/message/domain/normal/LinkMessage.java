package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

/**
 * ������Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class LinkMessage extends NormalMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 8811416273797547554L;

    /**
     * ������Ϣ
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public LinkMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
    }

    /**
     * ��Ϣ����
     * 
     * @return
     */
    public String getUrl() {
        return this.getString("Url");
    }

    /**
     * ��Ϣ����
     * 
     * @return
     */
    public String getTitle() {
        return this.getString("Title");
    }

    /**
     * ��Ϣ����
     * 
     * @return
     */
    public String getDescription() {
        return this.getString("Description");
    }

}

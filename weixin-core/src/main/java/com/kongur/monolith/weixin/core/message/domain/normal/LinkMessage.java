package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.features.LinkFeatures;

/**
 * ������Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class LinkMessage extends NormalMessage<LinkFeatures> {

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
        LinkFeatures features = new LinkFeatures(getString("Url"), getString("Title"), getString("Description"));
        
        this.setFeatures(features);
    }

    /**
     * ��Ϣ����
     * 
     * @return
     */
    public String getUrl() {
        return this.getFeatures().getUrl();
    }

    /**
     * ��Ϣ����
     * 
     * @return
     */
    public String getTitle() {
        return this.getFeatures().getTitle();
    }

    /**
     * ��Ϣ����
     * 
     * @return
     */
    public String getDescription() {
        return this.getFeatures().getDescription();

    }

}

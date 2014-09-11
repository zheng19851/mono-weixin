package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.features.TextFeatures;

/**
 * �ı���Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-18
 */
public class TextMessage extends NormalMessage<TextFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = -8766267454666750603L;

    /**
     * �ı���Ϣ
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public TextMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        setFeatures(new TextFeatures(getString("Content")));
    }

    /**
     * ����
     * 
     * @return
     */
    public String getContent() {
        return getFeatures().getContent();
    }

}

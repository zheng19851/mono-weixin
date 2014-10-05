package com.runssnail.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.TextFeatures;

/**
 * 文本消息
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
     * 文本消息
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
     * 内容
     * 
     * @return
     */
    public String getContent() {
        return getFeatures().getContent();
    }

}

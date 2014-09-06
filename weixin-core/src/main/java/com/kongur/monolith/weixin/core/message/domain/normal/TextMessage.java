package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

/**
 * 文本消息
 * 
 * @author zhengwei
 * @date 2014-2-18
 */
public class TextMessage extends NormalMessage {

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
    }

    /**
     * 
     */
    private static final long serialVersionUID = -8766267454666750603L;

    /**
     * 内容
     * 
     * @return
     */
    public String getContent() {
        return this.getString("Content");
    }

    @Override
    public String getFeatures() {
        return "{content:'" + this.getContent() + "'}";
    }

}

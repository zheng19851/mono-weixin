package com.kongur.monolith.weixin.core.message.domain;

import java.util.Map;

/**
 * 默认的消息对象
 * 
 * @author zhengwei
 * @date 2014-2-17
 */
public class DefaultMessage extends AbstractMessage {

    /**
     * 
     */
    private static final long serialVersionUID = -3468433657850979829L;

    /**
     * 默认的消息对象
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public DefaultMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(appId, signature, timestamp, nonce, params);
    }

}

package com.runssnail.monolith.weixin.core.message.domain;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.Features;

/**
 * 默认的消息对象
 * 
 * @author zhengwei
 * @date 2014-2-17
 */
public class DefaultMessage extends AbstractMessage<Features> {

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

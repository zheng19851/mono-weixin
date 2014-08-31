package com.kongur.monolith.weixin.core.message.domain.event;

import java.util.Map;

/**
 * ȡ����ע��Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class UnsubscribeEventMessage extends EventMessage {

    /**
     * ȡ����ע��Ϣ
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public UnsubscribeEventMessage(String appId, String signature, String timestamp, String nonce,
                                   Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 6931700961230934989L;

}

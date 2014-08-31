package com.kongur.monolith.weixin.core.message.domain.event;

import java.util.Map;

/**
 * ����˵���ת����ʱ���¼�����
 * 
 * @author zhengwei
 */
public class ViewEventMessage extends EventMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ViewEventMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
    }


}

package com.kongur.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.features.ClickEventFeatures;

/**
 * �Զ���˵��¼�
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class ClickEventMessage extends EventMessage<ClickEventFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = -4376011937062217577L;

    /**
     * �Զ���˵��¼�
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public ClickEventMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        this.setFeatures(new ClickEventFeatures(getEventType(), this.getString("EventKey")));
    }

    /**
     * �¼�KEYֵ�����Զ���˵��ӿ���KEYֵ��Ӧ
     * 
     * @return
     */
    public String getEventKey() {
        return this.getFeatures().getEventKey();
    }

}

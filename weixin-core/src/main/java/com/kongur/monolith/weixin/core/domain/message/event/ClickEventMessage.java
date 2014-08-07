package com.kongur.monolith.weixin.core.domain.message.event;

import java.util.Map;

/**
 * �Զ���˵��¼�
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class ClickEventMessage extends EventMessage {

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
    public ClickEventMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * �¼�KEYֵ�����Զ���˵��ӿ���KEYֵ��Ӧ
     * 
     * @return
     */
    public String getEventKey() {
        return this.getString("EventKey");
    }

}

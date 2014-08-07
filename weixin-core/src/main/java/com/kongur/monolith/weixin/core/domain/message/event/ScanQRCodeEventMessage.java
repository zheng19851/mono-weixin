package com.kongur.monolith.weixin.core.domain.message.event;

import java.util.Map;

/**
 * ɨ���������ά���¼�
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class ScanQRCodeEventMessage extends EventMessage {

    /**
     * 
     */
    private static final long serialVersionUID = -3125036471584087414L;

    /**
     * ɨ���������ά���¼�
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public ScanQRCodeEventMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * �¼�KEYֵ��qrscene_Ϊǰ׺������Ϊ��ά��Ĳ���ֵ
     * 
     * @return
     */
    public String getEventKey() {
        return this.getString("EventKey");
    }

    /**
     * ��ά���ticket����������ȡ��ά��ͼƬ
     * 
     * @return
     */
    public String getTicket() {
        return this.getString("Ticket");
    }

}

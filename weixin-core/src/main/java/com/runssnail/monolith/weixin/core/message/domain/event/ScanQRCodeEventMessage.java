package com.runssnail.monolith.weixin.core.message.domain.event;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.ScanQRCodeEventFeatures;

/**
 * ɨ���������ά���¼�
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class ScanQRCodeEventMessage extends EventMessage<ScanQRCodeEventFeatures> {

    /**
     * 
     */
    private static final long       serialVersionUID = -3125036471584087414L;


    /**
     * ɨ���������ά���¼�
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public ScanQRCodeEventMessage(String appId, String signature, String timestamp, String nonce,
                                  Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        ScanQRCodeEventFeatures features = new ScanQRCodeEventFeatures(getEventType(), getString("EventKey"), getString("Ticket"));
        this.setFeatures(features);
    }

    /**
     * �¼�KEYֵ��qrscene_Ϊǰ׺������Ϊ��ά��Ĳ���ֵ
     * 
     * @return
     */
    public String getEventKey() {
        return this.getFeatures().getEventKey();
    }

    /**
     * ��ά���ticket����������ȡ��ά��ͼƬ
     * 
     * @return
     */
    public String getTicket() {
        return this.getFeatures().getTicket();
    }

}

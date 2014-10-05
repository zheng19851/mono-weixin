package com.runssnail.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.VoiceFeatures;

/**
 * ������Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class VoiceMessage extends NormalMessage<VoiceFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = 3442377982279511620L;

    /**
     * ������Ϣ
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public VoiceMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        VoiceFeatures features = new VoiceFeatures(getString("MediaId"), getString("Format"));
        this.setFeatures(features);
    }

    /**
     * ������Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
     * 
     * @return
     */
    public String getMediaId() {
        return this.getFeatures().getMediaId();
    }

    /**
     * ������ʽ����amr��speex��
     * 
     * @return
     */
    public String getFormat() {
        return this.getFeatures().getFormat();
    }

}

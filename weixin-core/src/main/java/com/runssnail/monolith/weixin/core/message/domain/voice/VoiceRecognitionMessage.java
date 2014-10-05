package com.runssnail.monolith.weixin.core.message.domain.voice;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.AbstractMessage;
import com.runssnail.monolith.weixin.core.message.domain.features.VoiceRecognitionFeatures;

/**
 * ����ʶ������Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public class VoiceRecognitionMessage extends AbstractMessage<VoiceRecognitionFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = -4930721385706842976L;

    /**
     * ����ʶ������Ϣ
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public VoiceRecognitionMessage(String appId, String signature, String timestamp, String nonce,
                                   Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        VoiceRecognitionFeatures features = new VoiceRecognitionFeatures(getString("MediaId"), getString("Format"),
                                                                         getString("Recognition"));
        this.setFeatures(features);
    }

    /**
     * ������Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ��ý��
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

    /**
     * ����ʶ������UTF8����
     * 
     * @return
     */
    public String getRecognition() {
        return this.getFeatures().getRecognition();
    }

}

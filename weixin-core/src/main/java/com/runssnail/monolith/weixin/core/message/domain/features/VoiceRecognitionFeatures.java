package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * ����ʶ������Ϣ����
 * 
 * @author zhengwei
 */
public class VoiceRecognitionFeatures extends VoiceFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = -7002566721595660060L;
    
    /**
     * ����ʶ������UTF8����
     */
    private String recognition;

    public VoiceRecognitionFeatures(String mediaId, String format, String recognition) {

        super(mediaId, format);
        this.recognition = recognition;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

}

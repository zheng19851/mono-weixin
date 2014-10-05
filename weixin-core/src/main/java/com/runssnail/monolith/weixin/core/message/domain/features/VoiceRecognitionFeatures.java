package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * 语音识别结果消息特性
 * 
 * @author zhengwei
 */
public class VoiceRecognitionFeatures extends VoiceFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = -7002566721595660060L;
    
    /**
     * 语音识别结果，UTF8编码
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

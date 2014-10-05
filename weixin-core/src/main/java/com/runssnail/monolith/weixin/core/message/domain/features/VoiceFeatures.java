package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * …˘“Ù
 * 
 * @author zhengwei
 */
public class VoiceFeatures extends AbstractFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 1252237268048894483L;

    private String            mediaId;
    private String            format;

    public VoiceFeatures(String mediaId, String format) {
        this.mediaId = mediaId;
        this.format = format;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}

package com.kongur.monolith.weixin.core.message.domain.features;

/**
 * 视频消息特性
 * 
 * @author zhengwei
 */
public class VideoFeatures extends AbstractFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 7502702127067478316L;
    
    /**
     * 视频消息的媒体id
     */
    private String            mediaId;

    /**
     * 视频消息缩略图的媒体id
     */
    private String            thumbMediaId;

    public VideoFeatures(String mediaId, String thumbMediaId) {
        this.mediaId = mediaId;
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}

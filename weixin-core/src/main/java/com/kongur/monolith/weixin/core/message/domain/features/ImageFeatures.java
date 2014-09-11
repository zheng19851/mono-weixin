package com.kongur.monolith.weixin.core.message.domain.features;

/**
 * 图片消息特性
 * 
 * @author zhengwei
 */
public class ImageFeatures extends AbstractFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 3760175676603862719L;

    private String            mediaId;
    private String            picUrl;

    public ImageFeatures(String mediaId, String picUrl) {
        this.mediaId = mediaId;
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}

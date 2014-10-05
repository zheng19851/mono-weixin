package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * ��Ƶ��Ϣ����
 * 
 * @author zhengwei
 */
public class VideoFeatures extends AbstractFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 7502702127067478316L;
    
    /**
     * ��Ƶ��Ϣ��ý��id
     */
    private String            mediaId;

    /**
     * ��Ƶ��Ϣ����ͼ��ý��id
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

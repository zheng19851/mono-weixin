package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.features.VideoFeatures;

/**
 * 视频消息
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class VideoMessage extends NormalMessage<VideoFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = -7629121241656779367L;

    /**
     * 视频消息
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public VideoMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        VideoFeatures features = new VideoFeatures(getString("MediaId"), getString("ThumbMediaId"));
        this.setFeatures(features);
    }

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * 
     * @return
     */
    public String getMediaId() {
        return this.getFeatures().getMediaId();
    }

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     * 
     * @return
     */
    public String getThumbMediaId() {
        return this.getFeatures().getThumbMediaId();
    }

}

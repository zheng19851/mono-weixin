package com.runssnail.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.ImageFeatures;

/**
 * 图片消息
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class ImageMessage extends NormalMessage<ImageFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = 7577533032194810330L;

    /**
     * 图片消息
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public ImageMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        ImageFeatures features = new ImageFeatures(getString("MediaId"), getString("PicUrl"));
        this.setFeatures(features);
    }

    /**
     * 图片链接
     * 
     * @return
     */
    public String getPicUrl() {
        return this.getFeatures().getPicUrl();
    }

    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * 
     * @return
     */
    public String getMediaId() {
        return this.getFeatures().getMediaId();
    }

}

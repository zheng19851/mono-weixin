package com.runssnail.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.ImageFeatures;

/**
 * ͼƬ��Ϣ
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
     * ͼƬ��Ϣ
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
     * ͼƬ����
     * 
     * @return
     */
    public String getPicUrl() {
        return this.getFeatures().getPicUrl();
    }

    /**
     * ͼƬ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
     * 
     * @return
     */
    public String getMediaId() {
        return this.getFeatures().getMediaId();
    }

}

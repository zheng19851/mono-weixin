package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.features.VideoFeatures;

/**
 * ��Ƶ��Ϣ
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
     * ��Ƶ��Ϣ
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
     * ��Ƶ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
     * 
     * @return
     */
    public String getMediaId() {
        return this.getFeatures().getMediaId();
    }

    /**
     * ��Ƶ��Ϣ����ͼ��ý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
     * 
     * @return
     */
    public String getThumbMediaId() {
        return this.getFeatures().getThumbMediaId();
    }

}

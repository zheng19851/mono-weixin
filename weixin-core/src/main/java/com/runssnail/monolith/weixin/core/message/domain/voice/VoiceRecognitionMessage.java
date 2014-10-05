package com.runssnail.monolith.weixin.core.message.domain.voice;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.AbstractMessage;
import com.runssnail.monolith.weixin.core.message.domain.features.VoiceRecognitionFeatures;

/**
 * 语言识别结果消息
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public class VoiceRecognitionMessage extends AbstractMessage<VoiceRecognitionFeatures> {

    /**
     * 
     */
    private static final long serialVersionUID = -4930721385706842976L;

    /**
     * 语言识别结果消息
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public VoiceRecognitionMessage(String appId, String signature, String timestamp, String nonce,
                                   Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
        setAppId(appId);
        VoiceRecognitionFeatures features = new VoiceRecognitionFeatures(getString("MediaId"), getString("Format"),
                                                                         getString("Recognition"));
        this.setFeatures(features);
    }

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
     * 
     * @return
     */
    public String getMediaId() {
        return this.getFeatures().getMediaId();
    }

    /**
     * 语音格式，如amr，speex等
     * 
     * @return
     */
    public String getFormat() {
        return this.getFeatures().getFormat();
    }

    /**
     * 语音识别结果，UTF8编码
     * 
     * @return
     */
    public String getRecognition() {
        return this.getFeatures().getRecognition();
    }

}

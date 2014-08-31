package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

/**
 * ������Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class VoiceMessage extends NormalMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 3442377982279511620L;

    /**
     * ������Ϣ
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public VoiceMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * ������Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
     * 
     * @return
     */
    public String getMediaId() {
        return this.getString("MediaId");
    }

    /**
     * ������ʽ����amr��speex��
     * 
     * @return
     */
    public String getFormat() {
        return this.getString("Format");
    }

}

package com.kongur.monolith.weixin.core.domain.message.voice;

import java.util.Map;

import com.kongur.monolith.weixin.core.domain.message.AbstractMessage;

/**
 * ����ʶ������Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public class VoiceRecognitionMessage extends AbstractMessage {

    /**
     * 
     */
    private static final long serialVersionUID = -4930721385706842976L;

    /**
     * ����ʶ������Ϣ
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param params
     */
    public VoiceRecognitionMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    // /**
    // * ��Ϣid��64λ����
    // *
    // * @return
    // */
    // public String getMsgId() {
    // return this.getString("MsgId");
    // }

    /**
     * ������Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ��ý��
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

    /**
     * ����ʶ������UTF8����
     * 
     * @return
     */
    public String getRecognition() {
        return this.getString("Recognition");
    }

}

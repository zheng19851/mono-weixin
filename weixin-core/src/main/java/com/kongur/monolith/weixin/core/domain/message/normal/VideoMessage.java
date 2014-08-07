package com.kongur.monolith.weixin.core.domain.message.normal;

import java.util.Map;

/**
 * ��Ƶ��Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class VideoMessage extends NormalMessage {

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
    public VideoMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * ��Ƶ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
     * 
     * @return
     */
    public String getMediaId() {
        return this.getString("MediaId");
    }

    /**
     * ��Ƶ��Ϣ����ͼ��ý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
     * 
     * @return
     */
    public String getThumbMediaId() {
        return this.getString("ThumbMediaId");
    }

}

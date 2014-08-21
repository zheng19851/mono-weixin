package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

/**
 * ͼƬ��Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class ImageMessage extends NormalMessage {

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
    public ImageMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * ͼƬ����
     * 
     * @return
     */
    public String getPicUrl() {
        return this.getString("PicUrl");
    }

    /**
     * ͼƬ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ���ݡ�
     * 
     * @return
     */
    public String getMediaId() {
        return this.getString("MediaId");
    }

}

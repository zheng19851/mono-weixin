package com.kongur.monolith.weixin.core.message.domain.normal;

import java.util.Map;

import com.kongur.monolith.weixin.core.message.domain.AbstractMessage;
import com.kongur.monolith.weixin.core.message.domain.features.Features;

/**
 * ��ͨ����Ϣ����
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public class NormalMessage<F extends Features> extends AbstractMessage<F> {

    public NormalMessage(String signature, String timestamp, String nonce) {
        super(signature, timestamp, nonce);
    }

    public NormalMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        super(signature, timestamp, nonce, params);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 6939471631141552306L;

}

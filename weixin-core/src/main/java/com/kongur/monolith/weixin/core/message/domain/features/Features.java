package com.kongur.monolith.weixin.core.message.domain.features;

import java.io.Serializable;

/**
 * ��Ϣ����
 * 
 * @author zhengwei
 */
public interface Features extends Serializable {

    /**
     * ����json�ַ���
     * 
     * @return
     */
    String buildJson();
}

package com.kongur.monolith.weixin.common.domain.dto.custom;

/**
 * �ͻ���Ϣ����
 * 
 * @author zhengwei
 */
public interface CustomMessage {

    /**
     * ��Ϣ����
     * 
     * @return
     */
    String getMsgType();

    /**
     * ����Ϣ��
     * 
     * @return
     */
    String getToUser();

}

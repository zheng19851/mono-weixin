package com.kongur.monolith.weixin.client.custom.message;

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

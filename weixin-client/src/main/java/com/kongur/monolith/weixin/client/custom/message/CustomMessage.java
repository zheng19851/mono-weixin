package com.kongur.monolith.weixin.client.custom.message;

import java.io.Serializable;

/**
 * �ͻ���Ϣ����
 * 
 * @author zhengwei
 */
public interface CustomMessage extends Serializable {
    
    /**
     * ΢�Ź��ں�appid
     * 
     * @return
     */
    String getAppId();

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

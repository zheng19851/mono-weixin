package com.kongur.monolith.weixin.core.message.service;

import com.kongur.monolith.weixin.core.message.domain.Message;

/**
 * ��Ϣ�������
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public interface MessageService {

    /**
     * ����
     * 
     * @param msg
     * @return �ڲ��õ���ϢΨһID
     */
    String store(Message msg);

    /**
     * �Ƿ����
     * 
     * @param msg
     * @return
     */
    boolean contains(Message msg);

}

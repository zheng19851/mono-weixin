package com.kongur.monolith.weixin.core.service.message;

import com.kongur.monolith.weixin.core.domain.message.Message;

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

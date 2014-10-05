package com.runssnail.monolith.weixin.core.message.service;

import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.message.domain.features.Features;

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
    String store(Message<Features> msg);

    /**
     * �Ƿ����
     * 
     * @param msg
     * @return
     */
    boolean contains(Message<Features> msg);

}

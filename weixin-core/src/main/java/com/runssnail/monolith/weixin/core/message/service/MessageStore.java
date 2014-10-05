package com.runssnail.monolith.weixin.core.message.service;

import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.message.domain.features.Features;

/**
 * ��Ϣ�洢
 * 
 * @author zhengwei
 */
public interface MessageStore {

    /**
     * �洢����
     * 
     * @return
     */
    String getName();

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

package com.runssnail.monolith.weixin.core.message.dao;

import com.runssnail.monolith.weixin.core.message.domain.WXMessageDO;

public interface WXMessageDAO {

    Long insertMessage(WXMessageDO messageDO);

    WXMessageDO selectMessageByMsgId(String msgId);

    /**
     * ��ѯ΢����Ϣ
     * 
     * @param fromUser ���ͷ�
     * @param createTime ����ʱ��
     * @return
     */
    WXMessageDO selectMessage(String fromUser, long createTime);
}

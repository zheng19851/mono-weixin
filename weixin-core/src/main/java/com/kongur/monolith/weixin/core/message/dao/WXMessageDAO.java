package com.kongur.monolith.weixin.core.message.dao;

import com.kongur.monolith.weixin.core.message.domain.WXMessageDO;

public interface WXMessageDAO {

    Long insertMessage(WXMessageDO messageDO);

    WXMessageDO selectMessageByMsgId(String msgId);

    /**
     * 查询微信消息
     * 
     * @param fromUser 发送方
     * @param createTime 创建时间
     * @return
     */
    WXMessageDO selectMessage(String fromUser, long createTime);
}

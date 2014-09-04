package com.kongur.monolith.weixin.core.message.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.kongur.monolith.weixin.core.message.dao.WXMessageDAO;
import com.kongur.monolith.weixin.core.message.domain.WXMessageDO;

@Repository(value = "messageDAO")
public class MessageDAOImpl extends SqlMapClientDaoSupport implements WXMessageDAO {

    @Override
    public Long insertMessage(WXMessageDO messageDO) {

        return (Long) getSqlMapClientTemplate().insert("WX_MESSAGE.insertMessage", messageDO);

    }

    @Override
    public WXMessageDO selectMessageByMsgId(String msgId) {
        return (WXMessageDO) getSqlMapClientTemplate().queryForObject("WX_MESSAGE.selectMessageByMsgId", msgId);
    }

    @Override
    public WXMessageDO selectMessage(String fromUser, long createTime) {
        Map<String, Object> params = new HashMap<String, Object>(3);

        params.put("fromUser", fromUser);
        params.put("createTime", createTime);
        return (WXMessageDO) getSqlMapClientTemplate().queryForObject("WX_MESSAGE.selectMessage", params);
    }

}

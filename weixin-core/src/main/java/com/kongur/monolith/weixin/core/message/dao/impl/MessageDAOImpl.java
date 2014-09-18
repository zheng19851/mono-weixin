package com.kongur.monolith.weixin.core.message.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kongur.monolith.weixin.core.message.dao.WXMessageDAO;
import com.kongur.monolith.weixin.core.message.domain.WXMessageDO;

@Repository(value = "messageDAO")
public class MessageDAOImpl extends SqlSessionDaoSupport implements WXMessageDAO { // SqlMapClientDaoSupport

    @Override
    public Long insertMessage(WXMessageDO messageDO) {

        int id = getSqlSession().insert("WX_MESSAGE.insertMessage", messageDO);
        return Long.valueOf(id);
    }

    @Override
    public WXMessageDO selectMessageByMsgId(String msgId) {
        return (WXMessageDO) getSqlSession().selectOne("WX_MESSAGE.selectMessageByMsgId", msgId);
    }

    @Override
    public WXMessageDO selectMessage(String fromUser, long createTime) {
        Map<String, Object> params = new HashMap<String, Object>(3);

        params.put("fromUser", fromUser);
        params.put("createTime", createTime);
        return (WXMessageDO) getSqlSession().selectOne("WX_MESSAGE.selectMessage", params);
    }

}

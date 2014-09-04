package com.kongur.monolith.weixin.core.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kongur.monolith.weixin.core.message.dao.WXMessageDAO;
import com.kongur.monolith.weixin.core.message.domain.WXMessageDO;


public class MessageDAOTest extends DAOTestBase {

    @Autowired
    private WXMessageDAO messageDAO;
    
    @Test
    public void testInsert() {
        WXMessageDO msg = new WXMessageDO();
        msg.setAppId("fafa");
        msg.setMsgId("fafajlflj");
        msg.setMsgType("text");
        msg.setStatus(0);
        msg.setCreateTime(2749279427942L);
        msg.setFeatures("fafa");
        msg.setFromUser("fafaf");
        msg.setToUser("paaii");
        messageDAO.insertMessage(msg);
    }
    
//    @Test
    public void testSelect() {
        messageDAO.selectMessageByMsgId("fafa");
        
    }
    
}

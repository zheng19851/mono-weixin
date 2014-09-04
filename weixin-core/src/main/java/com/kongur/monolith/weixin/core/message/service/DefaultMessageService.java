package com.kongur.monolith.weixin.core.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.skyjoo.util.StringUtil;
import com.kongur.monolith.weixin.core.message.dao.WXMessageDAO;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.WXMessageDO;

/**
 * ��Ϣ���������ݿ���
 * 
 * @author zhengwei
 */
@Service("defaultMessageService")
public class DefaultMessageService implements MessageService {

    @Autowired
    private WXMessageDAO messageDAO;

    @Override
    public String store(Message msg) {
        WXMessageDO message = new WXMessageDO(msg);
        Long id = messageDAO.insertMessage(message);
        return String.valueOf(id);
    }

    @Override
    public boolean contains(Message msg) {
        WXMessageDO message = null;
        if (StringUtil.isNotBlank(msg.getMsgId())) {
            message = messageDAO.selectMessageByMsgId(msg.getMsgId());
        } else {
            message = messageDAO.selectMessage(msg.getFromUserName(), msg.getCreateTime());
        }
        return message != null;
    }

}

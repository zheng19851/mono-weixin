package com.kongur.monolith.weixin.core.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.skyjoo.util.StringUtil;
import com.kongur.monolith.weixin.core.message.dao.WXMessageDAO;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.WXMessageDO;
import com.kongur.monolith.weixin.core.message.domain.features.Features;

/**
 * 消息保存在数据库里
 * 
 * @author zhengwei
 */
@Service("defaultMessageService")
public class DefaultMessageService implements MessageService {

    @Autowired
    private WXMessageDAO messageDAO;

    @Override
    public String store(Message<Features> msg) {
        WXMessageDO message = new WXMessageDO(msg);
        Long id = messageDAO.insertMessage(message);
        return String.valueOf(id);
    }

    @Override
    public boolean contains(Message<Features> msg) {
        WXMessageDO message = null;
        if (StringUtil.isNotBlank(msg.getMsgId())) {
            message = messageDAO.selectMessageByMsgId(msg.getMsgId());
        } else {
            message = messageDAO.selectMessage(msg.getFromUserName(), msg.getCreateTime());
        }
        return message != null;
    }

}

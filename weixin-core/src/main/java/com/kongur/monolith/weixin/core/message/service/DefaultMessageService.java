package com.kongur.monolith.weixin.core.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.features.Features;

/**
 * 默认的消息服务实现
 * 
 * @author zhengwei
 */
@Service("defaultMessageService")
public class DefaultMessageService implements MessageService {

    // @Resource(name = "dbMessageStore")
    @Autowired
    private MessageStore defaultMessageStore;

    @Override
    public String store(Message<Features> msg) {
        return defaultMessageStore.store(msg);
    }

    @Override
    public boolean contains(Message<Features> msg) {
        return defaultMessageStore.contains(msg);
    }

}

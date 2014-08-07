package com.kongur.monolith.weixin.core.service.message.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.UUIDGenerator;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.common.LRUCache;
import com.kongur.monolith.weixin.core.domain.message.Message;
import com.kongur.monolith.weixin.core.domain.message.WrappedMessage;
import com.kongur.monolith.weixin.core.service.message.MessageService;

/**
 * ʹ���ڴ�����MessageService
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
@Service("messageService")
public class MemoryMessageService implements MessageService {

    private final Logger                           log      = Logger.getLogger(getClass());

    /**
     * �������
     */
    private int                                    capacity = 200;

    /**
     * �������ϢID�����ID���棬û�������FromUserName + CreateTime����
     */
    private final LRUCache<String, WrappedMessage> cache    = new LRUCache<String, WrappedMessage>(capacity);

    @Override
    public String store(Message msg) {

        String key = genKey(msg);

        // �ڲ���ϢID
        String id = UUIDGenerator.generate();
        WrappedMessage wm = new WrappedMessage(id, msg);

        this.cache.put(key, wm);

        if (log.isDebugEnabled()) {
            log.debug("store message successfully, msg=" + wm);
        }

        return id;
    }

    /**
     * ����key
     * 
     * @param msg
     * @return
     */
    private String genKey(Message msg) {

        String key = null;

        if (StringUtil.isNotBlank(msg.getMsgId())) {
            key = "msgId_" + msg.getMsgId();

        } else {
            key = msg.getFromUserName() + "_" + msg.getCreateTime();
        }

        return key;
    }

    @Override
    public boolean contains(Message msg) {

        String key = genKey(msg);

        return this.cache.containsKey(key);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}

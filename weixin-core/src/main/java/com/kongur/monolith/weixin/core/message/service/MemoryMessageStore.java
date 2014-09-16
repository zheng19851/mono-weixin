package com.kongur.monolith.weixin.core.message.service;

import org.springframework.stereotype.Service;

import com.kongur.monolith.common.UUIDGenerator;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.common.LRUCache;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.WrappedMessage;
import com.kongur.monolith.weixin.core.message.domain.features.Features;

/**
 * ���ڴ�ʵ�ֵ�MessageStore
 * 
 * @author zhengwei
 */
@Service("memoryMessageStore")
public class MemoryMessageStore extends AbstractMessageStore {

    private static final String                    NAME     = "memory";

    /**
     * �������
     */
    private int                                    capacity = 200;

    /**
     * �������ϢID�����ID���棬û�������FromUserName + CreateTime����
     */
    private final LRUCache<String, WrappedMessage> cache    = new LRUCache<String, WrappedMessage>(capacity);

    public MemoryMessageStore() {
        super(NAME);
    }

    @Override
    public String store(Message<Features> msg) {

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
    private String genKey(Message<Features> msg) {

        String key = null;

        if (StringUtil.isNotBlank(msg.getMsgId())) {
            key = "msgId_" + msg.getMsgId();

        } else {
            key = msg.getFromUserName() + "_" + msg.getCreateTime();
        }

        return key;
    }

    @Override
    public boolean contains(Message<Features> msg) {

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

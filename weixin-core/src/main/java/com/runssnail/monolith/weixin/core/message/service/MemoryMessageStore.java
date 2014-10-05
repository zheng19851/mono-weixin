package com.runssnail.monolith.weixin.core.message.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.UUIDGenerator;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.common.LRUCache;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.message.domain.WrappedMessage;
import com.runssnail.monolith.weixin.core.message.domain.features.Features;

/**
 * 用内存实现的MessageStore
 * 
 * @author zhengwei
 */
@Service("memoryMessageStore")
@Profile("msgUseMemory")
public class MemoryMessageStore extends AbstractMessageStore {

    private static final String                    NAME     = "memory";

    /**
     * 最大容量
     */
    private int                                    capacity = 200;

    /**
     * 如果有消息ID则根据ID保存，没有则根据FromUserName + CreateTime保存
     */
    private final LRUCache<String, WrappedMessage> cache    = new LRUCache<String, WrappedMessage>(capacity);

    public MemoryMessageStore() {
        super(NAME);
    }

    @Override
    public String store(Message<Features> msg) {

        String key = genKey(msg);

        // 内部消息ID
        String id = UUIDGenerator.generate();
        WrappedMessage wm = new WrappedMessage(id, msg);

        this.cache.put(key, wm);

        if (log.isDebugEnabled()) {
            log.debug("store message successfully, msg=" + wm);
        }

        return id;
    }

    /**
     * 生成key
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

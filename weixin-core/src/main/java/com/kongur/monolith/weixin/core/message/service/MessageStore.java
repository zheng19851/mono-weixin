package com.kongur.monolith.weixin.core.message.service;

import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.features.Features;

/**
 * 消息存储
 * 
 * @author zhengwei
 */
public interface MessageStore {

    /**
     * 存储名称
     * 
     * @return
     */
    String getName();

    /**
     * 保存
     * 
     * @param msg
     * @return 内部用的消息唯一ID
     */
    String store(Message<Features> msg);

    /**
     * 是否存在
     * 
     * @param msg
     * @return
     */
    boolean contains(Message<Features> msg);

}

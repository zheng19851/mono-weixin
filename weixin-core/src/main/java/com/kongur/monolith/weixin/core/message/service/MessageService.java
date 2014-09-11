package com.kongur.monolith.weixin.core.message.service;

import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.features.Features;

/**
 * 消息管理服务
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public interface MessageService {

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

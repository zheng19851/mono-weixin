package com.runssnail.monolith.weixin.core.message.service;

import com.runssnail.monolith.weixin.core.message.domain.Message;

/**
 * 查询符合要求的消息处理服务
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface MessageProcessServiceResolver {

    /**
     * 获取对应的消息服务，有先后顺序，哪个先匹配到就直接返回
     * 
     * @param msg
     * @return
     */
    MessageProcessService<Message> resolve(Message msg);

    /**
     * 添加MessageProcessService
     * 
     * @param service
     */
    boolean addMessageProcessService(MessageProcessService<Message> service);

    /**
     * 删除MessageProcessService
     * 
     * @param service
     * @return
     */
    boolean removeMessageProcessService(MessageProcessService<Message> service);

}

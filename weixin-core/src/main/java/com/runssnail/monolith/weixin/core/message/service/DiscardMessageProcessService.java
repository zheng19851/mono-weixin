package com.runssnail.monolith.weixin.core.message.service;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.message.domain.Message;

/**
 * 当找不到指定的消息处理服务时，就会调用这个服务
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Service("discardMessageProcessService")
@Order(Ordered.LOWEST_PRECEDENCE)
public class DiscardMessageProcessService extends AbstractMessageProcessService<Message> {

    @Override
    public boolean supports(Message msg) {
        return true;
    }

    @Override
    protected void doProcess(Message msg, Result<String> result) {

        log.warn("the message is discarded. msg=" + msg);

    }

}

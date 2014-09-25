package com.kongur.monolith.weixin.core.message.service;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.message.domain.Message;

/**
 * ���Ҳ���ָ������Ϣ�������ʱ���ͻ�����������
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

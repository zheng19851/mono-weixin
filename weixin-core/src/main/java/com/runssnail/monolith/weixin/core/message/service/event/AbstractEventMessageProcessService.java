package com.runssnail.monolith.weixin.core.message.service.event;

import com.runssnail.monolith.weixin.core.message.domain.EnumMessageType;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.message.domain.event.EventMessage;
import com.runssnail.monolith.weixin.core.message.service.AbstractMessageProcessService;

/**
 * 事件类型处理服务基类
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public abstract class AbstractEventMessageProcessService<M extends EventMessage> extends AbstractMessageProcessService<M> {

    @Override
    public boolean supports(Message msg) {
        return EnumMessageType.isEvent(msg.getMsgType());
    }

}

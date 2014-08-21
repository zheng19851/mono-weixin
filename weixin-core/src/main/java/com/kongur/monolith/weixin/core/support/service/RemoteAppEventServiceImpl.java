package com.kongur.monolith.weixin.core.support.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.domain.enums.EnumAppEventType;
import com.kongur.monolith.weixin.client.service.RemoteAppEventService;
import com.kongur.monolith.weixin.core.reply.manager.ErrorReplyManager;
import com.kongur.monolith.weixin.core.reply.manager.SubscribeReplyManager;

/**
 * @author zhengwei
 */
@Service("remoteAppEventService")
public class RemoteAppEventServiceImpl implements RemoteAppEventService {

    private final Logger          log = Logger.getLogger(getClass());

    @Autowired
    private SubscribeReplyManager subscribeReplyManager;

    @Autowired
    private ErrorReplyManager     errorReplyManager;

    @Override
    public Result<Object> onEvent(Object obj) {

        if (log.isDebugEnabled()) {
            log.debug("on event..., the obj=" + obj);
        }

        if (obj == null) {
            throw new RuntimeException("the event Object can not be null.");
        }

        Result<Object> result = new Result<Object>();
        if (EnumAppEventType.REFRESH_ERROR_REPLY.equals(obj)) {
            errorReplyManager.refresh();
        } else if (EnumAppEventType.REFRESH_SUBSCRIBE_REPLY.equals(obj)) {
            subscribeReplyManager.refresh();
        }

        result.setSuccess(true);
        return result;
    }

}

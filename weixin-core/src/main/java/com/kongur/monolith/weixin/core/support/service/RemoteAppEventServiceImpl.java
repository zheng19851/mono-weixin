package com.kongur.monolith.weixin.core.support.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.support.EnumAppEventType;
import com.kongur.monolith.weixin.client.support.RemoteAppEventService;
import com.kongur.monolith.weixin.core.mp.manager.PublicNoInfoManager;
import com.kongur.monolith.weixin.core.reply.manager.DefaultReplyManager;
import com.kongur.monolith.weixin.core.reply.manager.SubscribeReplyManager;
import com.kongur.monolith.weixin.core.support.event.AppEventMulticaster;

/**
 * @author zhengwei
 */
@Service("remoteAppEventService")
public class RemoteAppEventServiceImpl implements RemoteAppEventService {

    private final Logger          log = Logger.getLogger(getClass());

    @Autowired
    private SubscribeReplyManager subscribeReplyManager;

    @Autowired
    private DefaultReplyManager   errorReplyManager;

    @Autowired
    private PublicNoInfoManager   publicNoInfoManager;
    
    @Autowired
    private AppEventMulticaster appEventMulticaster;
 
    @Override
    public Result<Object> multicastEvent(Object event) {

        if (log.isDebugEnabled()) {
            log.debug("on event..., the event=" + event);
        }

        if (event == null) {
            throw new RuntimeException("the event Object can not be null.");
        }

        Result<Object> result = new Result<Object>();
        if (EnumAppEventType.REFRESH_ERROR_REPLY.equals(event)) {
            errorReplyManager.refresh();
        } else if (EnumAppEventType.REFRESH_SUBSCRIBE_REPLY.equals(event)) {
            subscribeReplyManager.refresh();
        } else if (EnumAppEventType.REFRESH_SUBSCRIBE_REPLY.equals(event)) {
            publicNoInfoManager.refresh();
        }

        result.setSuccess(true);
        return result;
    }

}

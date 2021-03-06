package com.runssnail.monolith.weixin.core.support.service;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.support.EnumAppEventType;
import com.runssnail.monolith.weixin.client.support.IAppEventService;
import com.runssnail.monolith.weixin.client.support.RemoteAppEvent;
import com.runssnail.monolith.weixin.core.base.service.AccessTokenService;
import com.runssnail.monolith.weixin.core.mp.manager.PublicNoInfoManager;
import com.runssnail.monolith.weixin.core.reply.manager.DefaultReplyManager;
import com.runssnail.monolith.weixin.core.reply.manager.SubscribeReplyManager;
import com.runssnail.monolith.weixin.core.support.event.AppEvent;
import com.runssnail.monolith.weixin.core.support.event.AppEventListener;
import com.runssnail.monolith.weixin.core.support.event.AppEventMulticaster;

/**
 * 默认IAppEventService实现
 * 
 * @author zhengwei
 */
@Service("appEventService")
public class DefaultAppEventService implements IAppEventService {

    private final Logger          log = Logger.getLogger(getClass());

    @Autowired
    private SubscribeReplyManager subscribeReplyManager;

    @Autowired
    private DefaultReplyManager   defaultReplyManager;

    @Autowired
    private PublicNoInfoManager   publicNoInfoManager;

    @Autowired
    private AppEventMulticaster   appEventMulticaster;

    @Autowired
    private AccessTokenService    accessTokenService;

    @PostConstruct
    public void init() {

        // 刷新默认回复消息设置
        appEventMulticaster.addAppEventListener(new AppEventListener<AppEvent>() {

            @Override
            public boolean supports(AppEvent event) {
                RemoteAppEvent source = (RemoteAppEvent) event.getSource();
                return source != null && EnumAppEventType.REFRESH_DEFAULT_REPLY.equals(source.getAppEventType());
            }

            @Override
            public void onEvent(AppEvent event) {
                defaultReplyManager.refresh();
            }
        });

        // 刷新订阅回复消息设置
        appEventMulticaster.addAppEventListener(new AppEventListener<AppEvent>() {

            @Override
            public boolean supports(AppEvent event) {
                RemoteAppEvent source = (RemoteAppEvent) event.getSource();
                return source != null && EnumAppEventType.REFRESH_SUBSCRIBE_REPLY.equals(source.getAppEventType());
            }

            @Override
            public void onEvent(AppEvent event) {
                subscribeReplyManager.refresh();
            }
        });

        // 刷新公众号设置
        appEventMulticaster.addAppEventListener(new AppEventListener<AppEvent>() {

            @Override
            public boolean supports(AppEvent event) {
                RemoteAppEvent source = (RemoteAppEvent) event.getSource();
                return source != null && EnumAppEventType.REFRESH_PUBLIC_NO.equals(source.getAppEventType());
            }

            @Override
            public void onEvent(AppEvent event) {
                publicNoInfoManager.refresh();
                RemoteAppEvent source = (RemoteAppEvent) event.getSource();
                accessTokenService.refresh(source.getResourceId());
            }
        });

    }

    @Override
    public Result<Object> multicastEvent(RemoteAppEvent event) {

        if (log.isDebugEnabled()) {
            log.debug("on event..., the event=" + event);
        }

        if (event == null) {
            throw new RuntimeException("the event Object can not be null.");
        }

        Result<Object> result = new Result<Object>();

        appEventMulticaster.multicastEvent(new AppEvent(event));

        result.setSuccess(true);
        return result;
    }

}

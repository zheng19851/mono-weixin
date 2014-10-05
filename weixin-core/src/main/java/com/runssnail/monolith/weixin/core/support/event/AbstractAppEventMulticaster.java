package com.runssnail.monolith.weixin.core.support.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * AppEventMulticaster抽象实现，提供基本注册功能
 * 
 * @author zhengwei
 */
public abstract class AbstractAppEventMulticaster implements AppEventMulticaster {

    protected final Logger                   log = Logger.getLogger(getClass());

    /**
     * 所有注册的监听器
     */
    private List<AppEventListener<AppEvent>> listeners;

    public void init() {
        if (this.listeners == null) {
            this.listeners = new ArrayList<AppEventListener<AppEvent>>();
        }
    }

    @Override
    public void addAppEventListener(AppEventListener<AppEvent> listener) {
        synchronized (this.listeners) {
            this.listeners.add(listener);
        }

    }

    /**
     * 获取支持当前事件的监听器
     * 
     * @param event 事件
     * @return
     */
    protected List<AppEventListener<AppEvent>> getSupportedAppListeners(AppEvent event) {
        List<AppEventListener<AppEvent>> list = new ArrayList<AppEventListener<AppEvent>>();
        for (AppEventListener<AppEvent> listener : this.listeners) {
            if (listener.supports(event)) {
                list.add(listener);
            }
        }
        return list;
    }

    /**
     * 所有注册的监听器
     * 
     * @return
     */
    protected List<AppEventListener<AppEvent>> getApplicationListeners() {
        return this.listeners;
    }

}

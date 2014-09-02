package com.kongur.monolith.weixin.core.support.event;

/**
 * 应用事件投递器
 * 
 * @author zhengwei
 */
public interface AppEventMulticaster {

    /**
     * 投递事件给支持的监听器(listener)
     * 
     * @param event 事件
     */
    void multicastEvent(AppEvent event);

    /**
     * 添加一个listener，来处理event
     * 
     * @param listener 监听器
     */
    void addAppEventListener(AppEventListener<AppEvent> listener);
}

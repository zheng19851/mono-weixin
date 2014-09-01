package com.kongur.monolith.weixin.core.support.event;


/**
 * 应用事件投递器
 * 
 * @author zhengwei
 */
public interface AppEventMulticaster {

    void multicastEvent(AppEvent event);

    void addAppEventListener(AppEventListener<AppEvent> listener);
}

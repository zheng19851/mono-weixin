package com.kongur.monolith.weixin.core.support.event;


/**
 * Ӧ���¼�Ͷ����
 * 
 * @author zhengwei
 */
public interface AppEventMulticaster {

    void multicastEvent(AppEvent event);

    void addAppEventListener(AppEventListener<AppEvent> listener);
}

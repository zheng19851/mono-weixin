package com.kongur.monolith.weixin.core.support.event;

import java.util.EventListener;

/**
 * 事件监听器
 * 
 * @author zhengwei
 */
public interface AppEventListener<E extends AppEvent> extends EventListener {

    /**
     * 处理事件
     * 
     * @param event
     */
    void onEvent(E event);

    /**
     * 是否支持处理当前事件
     * 
     * @param event
     * @return
     */
    boolean supports(E event);
}

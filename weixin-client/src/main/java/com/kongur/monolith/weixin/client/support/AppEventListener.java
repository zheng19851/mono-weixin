package com.kongur.monolith.weixin.client.support;

/**
 * 事件监听器
 * 
 * @author zhengwei
 */
public interface AppEventListener {

    /**
     * 处理事件
     * 
     * @param event
     */
    void onEvent(Object event);

    /**
     * 是否支持处理当前事件
     * 
     * @param event
     * @return
     */
    boolean supports(Object event);
}

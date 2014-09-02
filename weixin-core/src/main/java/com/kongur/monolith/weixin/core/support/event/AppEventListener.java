package com.kongur.monolith.weixin.core.support.event;

import java.util.EventListener;

/**
 * �¼�������
 * 
 * @author zhengwei
 */
public interface AppEventListener<E extends AppEvent> extends EventListener {

    /**
     * �����¼�
     * 
     * @param event
     */
    void onEvent(E event);

    /**
     * �Ƿ�֧�ִ���ǰ�¼�
     * 
     * @param event
     * @return
     */
    boolean supports(AppEvent event);
}

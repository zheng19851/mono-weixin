package com.kongur.monolith.weixin.core.support.service;

/**
 * �¼�������
 * 
 * @author zhengwei
 */
public interface AppEventListener {

    /**
     * �����¼�
     * 
     * @param event
     */
    void onEvent(Object event);

    /**
     * �Ƿ�֧�ִ���ǰ�¼�
     * 
     * @param event
     * @return
     */
    boolean supports(Object event);
}

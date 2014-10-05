package com.runssnail.monolith.weixin.core.support.event;

/**
 * Ӧ���¼�Ͷ����
 * 
 * @author zhengwei
 */
public interface AppEventMulticaster {

    /**
     * Ͷ���¼���֧�ֵļ�����(listener)
     * 
     * @param event �¼�
     */
    void multicastEvent(AppEvent event);

    /**
     * ���һ��listener��������event
     * 
     * @param listener ������
     */
    void addAppEventListener(AppEventListener<AppEvent> listener);
}

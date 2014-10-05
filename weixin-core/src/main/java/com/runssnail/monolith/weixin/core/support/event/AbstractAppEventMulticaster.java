package com.runssnail.monolith.weixin.core.support.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * AppEventMulticaster����ʵ�֣��ṩ����ע�Ṧ��
 * 
 * @author zhengwei
 */
public abstract class AbstractAppEventMulticaster implements AppEventMulticaster {

    protected final Logger                   log = Logger.getLogger(getClass());

    /**
     * ����ע��ļ�����
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
     * ��ȡ֧�ֵ�ǰ�¼��ļ�����
     * 
     * @param event �¼�
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
     * ����ע��ļ�����
     * 
     * @return
     */
    protected List<AppEventListener<AppEvent>> getApplicationListeners() {
        return this.listeners;
    }

}

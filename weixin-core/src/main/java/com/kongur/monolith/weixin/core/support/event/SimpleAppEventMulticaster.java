package com.kongur.monolith.weixin.core.support.event;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

/**
 * AppEventMulticasterºÚµ• µœ÷
 * 
 * @author zhengwei
 */
@Service("appEventMulticaster")
public class SimpleAppEventMulticaster extends AbstractAppEventMulticaster {

    private Executor taskExecutor;

    @PostConstruct
    public void init() {
        super.init();

        if (this.taskExecutor == null) {
            this.taskExecutor = Executors.newSingleThreadExecutor();
        }

    }

    @Override
    public void multicastEvent(final AppEvent event) {

        if (log.isDebugEnabled()) {
            log.debug("(multicastEvent)received event, event=" + event);
        }

        List<AppEventListener<AppEvent>> listeners = getSupportedAppListeners(event);

        if (log.isDebugEnabled()) {
            log.debug("(multicastEvent) get supported app listeners, listeners=" + listeners + ", event=" + event);
        }

        for (final AppEventListener<AppEvent> listener : listeners) {
            if (this.taskExecutor != null) {
                this.taskExecutor.execute(new Runnable() {

                    @Override
                    public void run() {
                        listener.onEvent(event);
                    }
                });
            } else {
                listener.onEvent(event);
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("(multicastEvent)process event end, event=" + event);
        }

    }

    public Executor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

}

package com.kongur.monolith.weixin.core.support.event;

import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.stereotype.Service;

/**
 * AppEventMulticasterºÚµ• µœ÷
 * 
 * @author zhengwei
 */
@Service("appEventMulticaster")
public class SimpleAppEventMulticaster extends AbstractAppEventMulticaster {

    private Executor taskExecutor;

    @Override
    public void multicastEvent(final AppEvent event) {

        List<AppEventListener<AppEvent>> listeners = getSupportedAppListeners(event);

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

    }

    public Executor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

}

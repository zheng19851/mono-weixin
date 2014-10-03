package com.kongur.monolith.weixin.core.base.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kongur.monolith.weixin.core.base.service.impl.DefaultApiService;
import com.kongur.monolith.weixin.core.base.service.impl.RetryApiService;

/**
 * @author zhengwei
 */
@Service("apiService")
public class ApiServiceFactoryBean implements FactoryBean<ApiService>, InitializingBean, DisposableBean {

    private ApiService apiService;

    /**
     * 默认重试一次
     */
    private int        retryCount = 1;

    /**
     * 重试等待间隔，默认300毫秒
     */
    @Value("${weixin.api.retry.period}")
    private long       retryWait  = 300;

    @Override
    public void afterPropertiesSet() throws Exception {
        DefaultApiService defaultApiService = new DefaultApiService();
        RetryApiService retryApiService = new RetryApiService();
        retryApiService.setDelegate(defaultApiService);
        retryApiService.setRetryCount(retryCount);
        retryApiService.setRetryWait(retryWait);
        retryApiService.init();

        this.apiService = retryApiService;
    }

    @Override
    public ApiService getObject() throws Exception {
        return apiService;
    }

    @Override
    public Class<?> getObjectType() {
        return ApiService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        this.apiService.close();
    }

}

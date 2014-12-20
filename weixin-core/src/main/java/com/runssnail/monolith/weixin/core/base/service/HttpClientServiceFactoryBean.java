package com.runssnail.monolith.weixin.core.base.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.weixin.core.base.service.impl.DefaultHttpClientService;
import com.runssnail.monolith.weixin.core.base.service.impl.RetryHttpClientService;

/**
 * @author zhengwei
 */
@Service("apiService")
public class HttpClientServiceFactoryBean implements FactoryBean<HttpClientService>, InitializingBean, DisposableBean {

    private HttpClientService httpClientService;

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
        DefaultHttpClientService defaultApiService = new DefaultHttpClientService();
        RetryHttpClientService retryApiService = new RetryHttpClientService();
        retryApiService.setDelegate(defaultApiService);
        retryApiService.setRetryCount(retryCount);
        retryApiService.setRetryWait(retryWait);
        retryApiService.init();

        this.httpClientService = retryApiService;
    }

    @Override
    public HttpClientService getObject() throws Exception {
        return httpClientService;
    }

    @Override
    public Class<?> getObjectType() {
        return HttpClientService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        this.httpClientService.close();
    }

}

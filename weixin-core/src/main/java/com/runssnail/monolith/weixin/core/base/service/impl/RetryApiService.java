package com.runssnail.monolith.weixin.core.base.service.impl;

import java.net.UnknownHostException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.base.service.ApiException;
import com.runssnail.monolith.weixin.core.base.service.ApiService;

/**
 * 提供重试功能，测试的时候经常会出java.net.UnknownHostException: api.weixin.qq.com
 * 
 * @author zhengwei
 */
public class RetryApiService implements ApiService {

    private final Logger  log           = Logger.getLogger(getClass());

    /**
     * 默认重试一次
     */
    private int           retryCount    = 1;

    /**
     * 重试等待间隔，默认300毫秒
     */
    @Value("${weixin.api.retry.period}")
    private long          retryWait     = 300;

    @Resource(name = "defaultApiService")
    private ApiService    delegate;

    private RetryTemplate retryTemplate = new RetryTemplate();

    @Override
    public Result<String> executeGet(final String apiUrl) throws ApiException {
        return retryTemplate.execute(new Callback<String>() {

            @Override
            public Result<String> doAction() {
                return delegate.executeGet(apiUrl);
            }
        });
    }

    @Override
    public Result<String> executeGet(final String apiUrl, final Map<String, String> getParams) throws ApiException {
        return retryTemplate.execute(new Callback<String>() {

            @Override
            public Result<String> doAction() {
                return delegate.executeGet(apiUrl, getParams);
            }
        });
    }

    @Override
    public Result<String> executePost(final String apiUrl, final String postParams) throws ApiException {
        return retryTemplate.execute(new Callback<String>() {

            @Override
            public Result<String> doAction() {
                return delegate.executePost(apiUrl, postParams);
            }
        });
    }

    @Override
    public Result<JSONObject> doGet(final String apiUrl) throws ApiException {
        return retryTemplate.execute(new Callback<JSONObject>() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doGet(apiUrl);
            }
        });
    }

    @Override
    public Result<JSONObject> doGet(final String apiUrl, final Map<String, String> getParams) throws ApiException {
        return retryTemplate.execute(new Callback<JSONObject>() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doGet(apiUrl, getParams);
            }
        });
    }

    @Override
    public Result<JSONObject> doPost(final String apiUrl, final String postParams) throws ApiException {
        return retryTemplate.execute(new Callback<JSONObject>() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doPost(apiUrl, postParams);
            }
        });
    }

    /**
     * 重试模板
     * 
     * @author zhengwei
     */
    private class RetryTemplate {

        public <T> Result<T> execute(Callback<T> callback) {
            Result<T> result = null;

            // 一次是肯定要执行的
            for (int i = 0; i <= retryCount; i++) {

                if (i > 0) {
                    log.warn("retry execute api (" + i + "), previous result=" + result);

                    if (retryWait > 0) {
                        // 等待一会再重试，间隔太短，没什么效果
                        try {
                            Thread.sleep(retryWait);
                        } catch (InterruptedException e) {
                            log.warn("retry wait is interrupted,", e);
                        }
                    }
                }

                try {

                    return callback.doAction();

                } catch (ApiException e) {
                    // 你妹的，测试的时候经常会出java.net.UnknownHostException: api.weixin.qq.com
                    if (!supports(e)) { // 是这个错误么，就重试..
                        throw e;
                    }

                    log.warn("find java.net.UnknownHostException: api.weixin.qq.com, so will retry.");
                }

            }

            return result;
        }
    }

    private boolean supports(Exception e) {
        // 你妹的，测试的时候经常会出java.net.UnknownHostException: api.weixin.qq.com
        return e.getCause() instanceof UnknownHostException;
    }

    interface Callback<T> {

        Result<T> doAction();
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getRetryWait() {
        return retryWait;
    }

    public void setRetryWait(long retryWait) {
        this.retryWait = retryWait;
    }

    public ApiService getDelegate() {
        return delegate;
    }

    public void setDelegate(ApiService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void init() {
        this.delegate.init();
    }

    @Override
    public void close() {
        this.delegate.close();
    }

}

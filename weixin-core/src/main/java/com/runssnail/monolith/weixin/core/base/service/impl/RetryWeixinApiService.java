package com.runssnail.monolith.weixin.core.base.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.base.service.AccessTokenService;
import com.runssnail.monolith.weixin.core.base.service.HttpException;
import com.runssnail.monolith.weixin.core.base.service.WeixinApiService;
import com.runssnail.monolith.weixin.core.support.WeixinApiHelper;

/**
 * 可支持重试服务的WeixinApiService实现
 * 
 * @author zhengwei
 */
// @Service("weixinApiService")
public class RetryWeixinApiService implements WeixinApiService {

    private final Logger       log           = Logger.getLogger(getClass());

    /**
     * 默认重试一次
     */
    private int                retryCount    = 1;

    /**
     * 重试等待间隔，默认300毫秒
     */
    @Value("${weixin.api.retry.period}")
    private long               retryWait     = 300;

    @Resource(name = "defaultWeixinApiService")
    private WeixinApiService   delegate;

    private RetryTemplate      retryTemplate = new RetryTemplate();

    @Autowired
    private AccessTokenService accessTokenService;

    public RetryWeixinApiService(WeixinApiService weixinApiService) {
        this.delegate = weixinApiService;
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl) throws HttpException {

        return this.doGet(apiUrl, true);
    }

    @Override
    public Result<JSONObject> doGet(final String apiUrl, final boolean replaceAccessToken) throws HttpException {
        return this.retryTemplate.execute(null, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doGet(apiUrl, replaceAccessToken);
            }
        });
    }

    @Override
    public Result<JSONObject> doGet(final String apiUrl, final Map<String, String> getParams,
                                    final boolean replaceAccessToken) throws HttpException {
        return this.retryTemplate.execute(null, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doGet(apiUrl, getParams, replaceAccessToken);
            }
        });
    }

    @Override
    public Result<JSONObject> doPost(final String apiUrl, final String postParams, final boolean replaceAccessToken)
                                                                                                                    throws HttpException {

        return this.retryTemplate.execute(null, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doPost(apiUrl, postParams, replaceAccessToken);
            }
        });
    }

    @Override
    public Result<JSONObject> doPost(final String apiUrl, final String postParams) throws HttpException {
        return this.retryTemplate.execute(null, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doPost(apiUrl, postParams);
            }
        });
    }

    @Override
    public Result<JSONObject> doPost(final String appId, final String apiUrl, final String postParams)
                                                                                                      throws HttpException {
        return this.retryTemplate.execute(null, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doPost(appId, apiUrl, postParams);
            }
        });
    }

    public Result<JSONObject> doPost(final String appId, final String apiUrl, final String postParams,
                                     final boolean replaceAccessToken) {
        return retryTemplate.execute(appId, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doPost(appId, apiUrl, postParams, replaceAccessToken);
            }
        });
    }

    @Override
    public Result<JSONObject> doGet(final String appId, final String apiUrl, final Map<String, String> getParams,
                                    final boolean replaceAccessToken) {
        return this.retryTemplate.execute(appId, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doGet(appId, apiUrl, getParams, replaceAccessToken);
            }
        });
    }

    @Override
    public Result<JSONObject> doGet(final String appId, final String apiUrl) {
        return this.retryTemplate.execute(appId, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doGet(appId, apiUrl);
            }
        });
    }

    /**
     * 重试模板
     * 
     * @author zhengwei
     */
    private class RetryTemplate {

        public Result<JSONObject> execute(String appId, Callback callback) {
            Result<JSONObject> result = null;

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

                result = callback.doAction();
                if (result.isSuccess()) {
                    return result;
                }

                if (isAccessTokenInvalid(result)) {
                    if (log.isInfoEnabled()) {
                        log.info("access token is invalid, so refresh. appId=" + appId);
                    }

                    // 刷新access token
                    if (StringUtil.isNotBlank(appId)) {
                        accessTokenService.refresh(appId);
                    } else {
                        accessTokenService.refreshDefault();
                    }
                } else {
                    return result;
                }

            }

            return result;
        }
    }

    interface Callback {

        Result<JSONObject> doAction();
    }

    /**
     * 可重试原因，出错了&&是access_token过期
     * 
     * @param result
     * @return
     */
    private boolean isAccessTokenInvalid(Result<JSONObject> result) {
        return WeixinApiHelper.isAccessTokenInvalid(result.getResult());
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

    public AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    public void setAccessTokenService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

}

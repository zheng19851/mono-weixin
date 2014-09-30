package com.kongur.monolith.weixin.core.base.service.impl;

import java.net.UnknownHostException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.base.service.AccessTokenService;
import com.kongur.monolith.weixin.core.base.service.ApiException;
import com.kongur.monolith.weixin.core.base.service.WeixinApiService;
import com.kongur.monolith.weixin.core.mp.service.PublicNoInfoService;
import com.kongur.monolith.weixin.core.support.WeixinApiHelper;

/**
 * ��֧�����Է����WeixinApiServiceʵ��
 * 
 * @author zhengwei
 */
@Service("weixinApiService")
public class RetryWeixinApiService implements WeixinApiService {

    private final Logger        log           = Logger.getLogger(getClass());

    /**
     * Ĭ������һ��
     */
    private int                 retryCount    = 1;

    /**
     * ���Եȴ������Ĭ��300����
     */
    @Value("${weixin.api.retry.period}")
    private long                retryWait     = 300;

    @Resource(name = "defaultWeixinApiService")
    private WeixinApiService    delegate;

    private RetryTemplate       retryTemplate = new RetryTemplate();

    @Autowired
    private PublicNoInfoService publicNoInfoService;

    @Autowired
    private AccessTokenService  accessTokenService;

    @Override
    public Result<JSONObject> doGet(String apiUrl) throws ApiException {

        return this.doGet(apiUrl, true);
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl, boolean replaceAccessToken) throws ApiException {
        return this.doGet(apiUrl, null, replaceAccessToken);
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl, Map<String, String> getParams, boolean replaceAccessToken)
                                                                                                             throws ApiException {
        return this.doGet(this.publicNoInfoService.getDefaultAppId(), apiUrl, getParams, replaceAccessToken);
    }

    @Override
    public Result<JSONObject> doPost(String apiUrl, String postParams, boolean replaceAccessToken) throws ApiException {
        return this.doPost(this.publicNoInfoService.getDefaultAppId(), apiUrl, postParams, replaceAccessToken);
    }

    @Override
    public Result<JSONObject> doPost(String apiUrl, String postParams) throws ApiException {
        return this.doPost(apiUrl, postParams, true);
    }

    @Override
    public Result<JSONObject> doPost(String appId, String apiUrl, String postParams) throws ApiException {
        return this.doPost(appId, apiUrl, postParams, true);
    }

    public Result<JSONObject> doPost(final String appId, final String apiUrl, final String postParams,
                                     final boolean replaceAccessToken) {
        return retryTemplate.execute(appId, apiUrl, replaceAccessToken, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doPost(appId, apiUrl, postParams, replaceAccessToken);
            }
        });
    }

    @Override
    public Result<JSONObject> doGet(final String appId, final String apiUrl, final Map<String, String> getParams,
                                    final boolean replaceAccessToken) {
        return this.retryTemplate.execute(appId, apiUrl, replaceAccessToken, new Callback() {

            @Override
            public Result<JSONObject> doAction() {
                return delegate.doGet(appId, apiUrl, getParams, replaceAccessToken);
            }
        });
    }

    @Override
    public Result<JSONObject> doGet(String appId, String apiUrl) {
        return this.doGet(appId, apiUrl, null, true);
    }

    /**
     * ����ģ��
     * 
     * @author zhengwei
     */
    private class RetryTemplate {

        public Result<JSONObject> execute(String appId, String apiUrl, boolean replaceAccessToken, Callback callback) {
            Result<JSONObject> result = null;

            // һ���ǿ϶�Ҫִ�е�
            for (int i = 0; i <= retryCount; i++) {

                try {

                    result = callback.doAction();
                    if (result.isSuccess()) {
                        return result;
                    }

                    if (isAccessTokenInvalid(result)) {
                        log.warn("access token is invalid, so refresh. appId=" + appId);
                        // ˢ��access token
                        accessTokenService.refresh(appId);
                    } else {
                        return result;
                    }

                } catch (ApiException e) {
                    // ���õģ����Ե�ʱ�򾭳����java.net.UnknownHostException: api.weixin.qq.com
                    if (!supports(e)) { // ���������ô��������..
                        throw e;
                    }
                }

                log.warn("retry execute api (" + i + "), previous result=" + result);

                // �ȴ�һ�������ԣ����̫�̣�ûʲôЧ��
                try {
                    Thread.sleep(retryWait);
                } catch (InterruptedException e) {
                    log.warn("retry wait is interrupted,", e);
                }

            }

            return result;
        }
    }

    interface Callback {

        Result<JSONObject> doAction();
    }

    /**
     * ������ԭ�򣬳�����&&��access_token����
     * 
     * @param result
     * @return
     */
    private boolean isAccessTokenInvalid(Result<JSONObject> result) {
        return WeixinApiHelper.isAccessTokenInvalid(result.getResult());
    }

    private boolean supports(Exception e) {
        // ���õģ����Ե�ʱ�򾭳����java.net.UnknownHostException: api.weixin.qq.com
        return e.getCause() instanceof UnknownHostException;
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

}

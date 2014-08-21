package com.kongur.monolith.weixin.core.base.service.impl;

import java.net.UnknownHostException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.base.service.AccessTokenService;
import com.kongur.monolith.weixin.core.base.service.ApiException;
import com.kongur.monolith.weixin.core.base.service.ApiService;
import com.kongur.monolith.weixin.core.base.service.WeixinApiService;
import com.kongur.monolith.weixin.core.support.WeixinApiHelper;

/**
 * Ĭ�ϵ�΢�� api ����ӿڣ��ṩ���Թ��ܣ��Լ�������access_token
 * 
 * @author zhengwei
 * @date 2014��2��26��
 */
@Service("defaultWeixinApiService")
public class DefaultWeixinApiService implements WeixinApiService {

    private final Logger              log            = Logger.getLogger(getClass());

    @Resource(name = "defaultApiService")
    private ApiService                apiService;

    private final RetryTemplate       retryTemplate  = new RetryTemplate();

    /**
     * �滻��������
     */
    @Autowired
    private PropertyPlaceholderHelper propertyPlaceholderHelper;

    /**
     * Ĭ������һ��
     */
    private int                       retryCount     = 1;

    @Resource(name = "defaultAccessTokenService")
    private AccessTokenService        accessTokenService;

    /**
     * access_token_key
     */
    private String                    accessTokenKey = "access_token";

    /**
     * ���Եȴ������Ĭ��300����
     */
    @Value("${weixin.api.retry.period}")
    private long                      retryWait      = 300;

    protected boolean supports(Exception e) {
        // ���õģ����Ե�ʱ�򾭳����java.net.UnknownHostException: api.weixin.qq.com
        return e.getCause() instanceof UnknownHostException;
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl, boolean replaceAccessToken) throws ApiException {
        return this.doGet(apiUrl, null, replaceAccessToken);
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl, final Map<String, String> getParams, boolean replaceAccessToken)
                                                                                                                         throws ApiException {
        return retryTemplate.execute(apiUrl, replaceAccessToken, new Callback() {

            @Override
            public Result<JSONObject> doAction(String internalApiUrl) {
                return apiService.doGet(internalApiUrl, getParams);
            }
        });
    }

    @Override
    public Result<JSONObject> doPost(String apiUrl, final String postParams, boolean replaceAccessToken)
                                                                                                              throws ApiException {
        return retryTemplate.execute(apiUrl, replaceAccessToken, new Callback() {

            @Override
            public Result<JSONObject> doAction(String internalApiUrl) {
                return apiService.doPost(internalApiUrl, postParams);
            }
        });
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl) throws ApiException {
        return this.doGet(apiUrl, true);
    }

    @Override
    public Result<JSONObject> doPost(String apiUrl, String postParams) throws ApiException {
        return doPost(apiUrl, postParams, true);
    }

    /**
     * ������ԭ�򣬳�����&&��access_token����
     * 
     * @param result
     * @return
     */
    protected boolean supports(Result<JSONObject> result) {
        return !result.isSuccess() && WeixinApiHelper.isAccessTokenInvalid(result.getResult());
    }

    private String appendAccessToken(String apiUrl, String accessToken) {
        String internalApiUrl = apiUrl;

        // ���û�� ��ֱ�ӷ���
        // if (!apiUrl.contains(this.accessTokenKey)) {
        // return internalApiUrl;
        // }

        if (!internalApiUrl.contains("?")) {
            internalApiUrl = internalApiUrl + "?";
        }

        // �б�Ĳ���
        if (internalApiUrl.contains("=")) {
            internalApiUrl = internalApiUrl + "&";
        }

        // ����access_token����
        internalApiUrl = internalApiUrl + this.accessTokenKey + "=" + accessToken;

        return internalApiUrl;
    }

    /**
     * ����ģ��
     * 
     * @author zhengwei
     */
    private class RetryTemplate {

        public Result<JSONObject> execute(String apiUrl, boolean replaceAccessToken, Callback callback) {
            Result<JSONObject> result = new Result<JSONObject>();

            String internalApiUrl = apiUrl;

            if (replaceAccessToken) {
                // internalApiUrl = appendAccessToken(apiUrl, accessTokenService.getAccessToken());
                internalApiUrl = replaceAccessTokenKey(apiUrl, accessTokenService.getAccessToken());
            }

            // result = executeInternal(internalApiUrl, callback);

            try {

                result = callback.doAction(internalApiUrl);

                if (!supports(result)) {
                    return result;
                }

            } catch (ApiException e) {
                // ���õģ����Ե�ʱ�򾭳����java.net.UnknownHostException: api.weixin.qq.com
                if (!supports(e)) { // ���������ô��������..
                    throw e;
                }
            }

            for (int i = 1; i <= retryCount; i++) {

                log.warn("retry execute api (" + i + "), previous result=" + result);

                // �ȴ�һ�������ԣ����̫�̣�ûʲôЧ��
                try {
                    Thread.sleep(retryWait);
                } catch (InterruptedException e) {
                    log.warn("retry wait is interrupted,", e);
                }

                // ǰ����access_token�������Ҫˢ��
                if (replaceAccessToken && WeixinApiHelper.isAccessTokenInvalid(result.getResult())) {
                    // ��ˢ��
                    Result<String> refreshResult = accessTokenService.refresh();
                    if (!refreshResult.isSuccess()) {
                        return result;
                    }

                    // ˢ�º��access_token
                    // internalApiUrl = appendAccessToken(apiUrl, refreshResult.getResult());
                    internalApiUrl = replaceAccessTokenKey(apiUrl, refreshResult.getResult());

                }

                // result = executeInternal(internalApiUrl, callback);

                try {

                    result = callback.doAction(internalApiUrl);
                    if (!supports(result)) {
                        return result;
                    }

                } catch (ApiException e) {
                    // ���õģ����Ե�ʱ�򾭳����java.net.UnknownHostException: api.weixin.qq.com
                    if (!supports(e)) { // ���������ô��������..
                        throw e;
                    }
                }

            }

            return result;
        }

        /**
         * �滻AccessTokenKeyռλ��
         * 
         * @param apiUrl
         * @return
         */
        private String replaceAccessTokenKey(String apiUrl, final String accessToken) {
            String replacedApiUrl = propertyPlaceholderHelper.replacePlaceholders(apiUrl, new PlaceholderResolver() {

                @Override
                public String resolvePlaceholder(String placeholderName) {
                    if (accessTokenKey.equals(placeholderName)) {
                        return accessToken;
                    }
                    return null;
                }
            });

            return replacedApiUrl;
        }

        /**
         * ִ��
         * 
         * @param internalApiUrl
         * @param callback
         * @return
         */
        private Result<JSONObject> executeInternal(String internalApiUrl, Callback callback) {

            Result<JSONObject> result = new Result<JSONObject>();
            try {

                result = callback.doAction(internalApiUrl);

            } catch (ApiException e) {
                // ���õģ����Ե�ʱ�򾭳����java.net.UnknownHostException: api.weixin.qq.com
                if (!supports(e)) { // ���������ô��������..
                    throw e;
                }
            }

            return result;
        }

    }

    interface Callback {

        Result<JSONObject> doAction(String internalApiUrl);
    }

    public long getRetryWait() {
        return retryWait;
    }

    public void setRetryWait(long retryWait) {
        this.retryWait = retryWait;
    }

}

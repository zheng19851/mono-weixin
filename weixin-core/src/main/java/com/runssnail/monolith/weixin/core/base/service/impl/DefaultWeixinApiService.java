package com.runssnail.monolith.weixin.core.base.service.impl;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.base.service.AccessTokenService;
import com.runssnail.monolith.weixin.core.base.service.ApiException;
import com.runssnail.monolith.weixin.core.base.service.ApiService;
import com.runssnail.monolith.weixin.core.base.service.WeixinApiService;
import com.runssnail.monolith.weixin.core.mp.service.PublicNoInfoService;

/**
 * Ĭ�ϵ�΢�� api ����ӿڣ��ṩ������access_token
 * 
 * @author zhengwei
 * @date 2014��2��26��
 */
// @Service("defaultWeixinApiService")
public class DefaultWeixinApiService implements WeixinApiService {

    private final Logger              log            = Logger.getLogger(getClass());

    /**
     * access_token_key
     */
    private String                    accessTokenKey = "access_token";

    @Autowired
    private ApiService                apiService;

    /**
     * �滻��������
     */
    @Autowired
    private PropertyPlaceholderHelper propertyPlaceholderHelper;

    @Autowired
    private AccessTokenService        accessTokenService;

    @Autowired
    private PublicNoInfoService       publicNoInfoService;

    @Override
    public Result<JSONObject> doGet(String apiUrl, boolean replaceAccessToken) throws ApiException {
        return this.doGet(apiUrl, null, replaceAccessToken);
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl, final Map<String, String> getParams, boolean appendAccessToken)
                                                                                                                  throws ApiException {
        return this.doGet(publicNoInfoService.getDefaultAppId(), apiUrl, getParams, appendAccessToken);

    }

    @Override
    public Result<JSONObject> doPost(String apiUrl, final String postParams, boolean replaceAccessToken)
                                                                                                        throws ApiException {

        return this.doPost(this.publicNoInfoService.getDefaultAppId(), apiUrl, postParams, replaceAccessToken);

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

    @Override
    public Result<JSONObject> doPost(String appId, String apiUrl, String postParams) throws ApiException {
        return doPost(appId, apiUrl, postParams, true);
    }

    public Result<JSONObject> doPost(String appId, String apiUrl, final String postParams, boolean replaceAccessToken) {

        String internalApiUrl = apiUrl;
        if (replaceAccessToken) {
            internalApiUrl = replaceAccessTokenKey(apiUrl, accessTokenService.getAccessToken(appId));
        }

        return apiService.doPost(internalApiUrl, postParams);

    }

    @Override
    public Result<JSONObject> doGet(String appId, String apiUrl, final Map<String, String> getParams,
                                    boolean replaceAccessToken) {

        String internalApiUrl = apiUrl;
        if (replaceAccessToken) {
            internalApiUrl = replaceAccessTokenKey(apiUrl, accessTokenService.getAccessToken(appId));
        }

        return apiService.doGet(internalApiUrl, getParams);

    }

    @Override
    public Result<JSONObject> doGet(String appId, String apiUrl) {
        return this.doGet(appId, apiUrl, null, true);
    }

    public String getAccessTokenKey() {
        return accessTokenKey;
    }

    public void setAccessTokenKey(String accessTokenKey) {
        this.accessTokenKey = accessTokenKey;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public PropertyPlaceholderHelper getPropertyPlaceholderHelper() {
        return propertyPlaceholderHelper;
    }

    public void setPropertyPlaceholderHelper(PropertyPlaceholderHelper propertyPlaceholderHelper) {
        this.propertyPlaceholderHelper = propertyPlaceholderHelper;
    }

    public AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    public void setAccessTokenService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public PublicNoInfoService getPublicNoInfoService() {
        return publicNoInfoService;
    }

    public void setPublicNoInfoService(PublicNoInfoService publicNoInfoService) {
        this.publicNoInfoService = publicNoInfoService;
    }

}

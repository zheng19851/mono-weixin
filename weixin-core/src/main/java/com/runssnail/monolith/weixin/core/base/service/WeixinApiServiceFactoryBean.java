package com.runssnail.monolith.weixin.core.base.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;

import com.runssnail.monolith.weixin.core.base.service.impl.DefaultWeixinApiService;
import com.runssnail.monolith.weixin.core.base.service.impl.RetryWeixinApiService;
import com.runssnail.monolith.weixin.core.mp.service.PublicNoInfoService;

/**
 * @author zhengwei
 */
@Service("weixinApiService")
public class WeixinApiServiceFactoryBean implements FactoryBean<WeixinApiService>, InitializingBean {

    /**
     * 实际使用的对象
     */
    private WeixinApiService          weixinApiService;

    /**
     * 默认重试一次
     */
    private int                       retryCount     = 1;

    /**
     * access_token_key
     */
    private String                    accessTokenKey = "access_token";

    @Autowired
    private HttpClientService                apiService;

    /**
     * 替换变量名用
     */
    @Autowired
    private PropertyPlaceholderHelper propertyPlaceholderHelper;

    @Autowired
    private AccessTokenService        accessTokenService;

    @Autowired
    private PublicNoInfoService       publicNoInfoService;

    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.isTrue(this.retryCount >= 0, "retryCount must >= 0");
        Assert.notNull(accessTokenKey, "accessTokenKey can not be null.");

        DefaultWeixinApiService defaultWeixinApiService = new DefaultWeixinApiService();
        defaultWeixinApiService.setAccessTokenKey(accessTokenKey);
        defaultWeixinApiService.setAccessTokenService(accessTokenService);
        defaultWeixinApiService.setApiService(apiService);
        defaultWeixinApiService.setPropertyPlaceholderHelper(propertyPlaceholderHelper);
        defaultWeixinApiService.setPublicNoInfoService(publicNoInfoService);

        RetryWeixinApiService weixinApiService = new RetryWeixinApiService(defaultWeixinApiService);
        weixinApiService.setRetryCount(retryCount);
        weixinApiService.setAccessTokenService(accessTokenService);

        this.weixinApiService = defaultWeixinApiService;
    }

    @Override
    public WeixinApiService getObject() throws Exception {
        return this.weixinApiService;
    }

    @Override
    public Class<?> getObjectType() {
        return WeixinApiService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public WeixinApiService getWeixinApiService() {
        return weixinApiService;
    }

    public void setWeixinApiService(WeixinApiService weixinApiService) {
        this.weixinApiService = weixinApiService;
    }

    public String getAccessTokenKey() {
        return accessTokenKey;
    }

    public void setAccessTokenKey(String accessTokenKey) {
        this.accessTokenKey = accessTokenKey;
    }

    public HttpClientService getApiService() {
        return apiService;
    }

    public void setApiService(HttpClientService apiService) {
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

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

}

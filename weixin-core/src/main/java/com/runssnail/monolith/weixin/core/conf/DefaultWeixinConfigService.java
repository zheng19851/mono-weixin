package com.runssnail.monolith.weixin.core.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 微信配置服务
 * 
 * @author zhengwei
 */
@Service("weixinConfigService")
public class DefaultWeixinConfigService implements WeixinConfigService {

    /**
     * 微信公众号appid
     */
    @Value("${weixin.appId}")
    private String appId;

    /**
     * 财付通商户权限密钥Key
     */
    @Value("${weixin.payment.paternerKey}")
    private String paternerKey;

    @Value("${weixin.appSecret}")
    private String appSecret;

    /**
     * token
     */
    @Value("${weixin.token}")
    private String token;

    @PostConstruct
    public void init() {

        Assert.notNull(this.appId, "请设置appId");
        Assert.notNull(this.paternerKey, "请设置paternerKey");
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String getPaternerKey() {
        return this.paternerKey;
    }

    @Override
    public String getAppSecret() {
        return this.appSecret;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public boolean isDefaultAppId(String appId) {
        return this.appId.equals(appId);
    }

}

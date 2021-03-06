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

    /**
     * EncodingAesKey
     */
    @Value("${weixin.encodingAesKey}")
    private String encodingAesKey;

    @Value("${weixin.paySignkey}")
    private String paySignkey;

    /**
     * 商户号，非partnerId
     */
    @Value("${weixin.merchantId}")
    private String merchantId;

    @PostConstruct
    public void init() {

        Assert.notNull(this.appId, "请设置appId");
        Assert.notNull(this.paternerKey, "请设置paternerKey");
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
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

    @Override
    public String getPaySignkey() {
        // 支付密钥
        return paySignkey;
    }

    public void setPaySignkey(String paySignkey) {
        this.paySignkey = paySignkey;
    }

    public void setPaternerKey(String paternerKey) {
        this.paternerKey = paternerKey;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

}

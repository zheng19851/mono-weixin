package com.kongur.monolith.weixin.pay.demo.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author zhengwei
 */
@Service("weixinConfigService")
public class DefaultWeixinConfigService implements WeixinConfigService {

    /**
     * 财付通商户权限密钥Key
     */
    @Value("weixin.payment.paternerKey")
    private String paternerKey;

    /**
     * 财付通商户身份的标识
     */
    @Value("weixin.payment.partnerId")
    private String partnerId;

    /**
     * 接收微信通知的url
     */
    @Value("weixin.payment.notifyUrl")
    private String notifyUrl;

    /**
     * 微信公众号appid
     */
    @Value("weixin.platform.appId")
    private String appId;

    /**
     * 付款密钥
     */
    @Value("weixin.payment.paySignkey")
    private String paySignkey;

    /**
     * 默认字符集
     */
    @Value("weixin.payment.charset")
    private String charset = "GBK";

    @PostConstruct
    public void init() {
        Assert.notNull(this.paternerKey, "请设置paternerKey");
        Assert.notNull(this.partnerId, "请设置partnerId");

        Assert.notNull(this.notifyUrl, "请设置notifyUrl");

        Assert.notNull(this.appId, "请设置appId");
        Assert.notNull(this.paySignkey, "请设置paySignkey");
        Assert.notNull(this.charset, "请设置charset");
    }

    public String getPaternerKey() {
        return this.paternerKey;
    }

    public void setPaternerKey(String paternerKey) {
        this.paternerKey = paternerKey;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPaySignkey() {
        return paySignkey;
    }

    public void setPaySignkey(String paySignkey) {
        this.paySignkey = paySignkey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

}

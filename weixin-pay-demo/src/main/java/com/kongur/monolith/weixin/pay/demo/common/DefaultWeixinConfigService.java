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
     * �Ƹ�ͨ�̻�Ȩ����ԿKey
     */
    @Value("${weixin.payment.paternerKey}")
    private String paternerKey;

    /**
     * �Ƹ�ͨ�̻���ݵı�ʶ
     */
    @Value("${weixin.payment.partnerId}")
    private String partnerId;

    /**
     * ����΢��֪ͨ��url
     */
    @Value("${weixin.payment.notifyUrl}")
    private String notifyUrl;

    /**
     * ΢�Ź��ں�appid
     */
    @Value("${weixin.appId}")
    private String appId;

    /**
     * ������Կ
     */
    @Value("${weixin.payment.paySignkey}")
    private String paySignkey;

    /**
     * Ĭ���ַ���
     */
    @Value("${weixin.payment.charset}")
    private String charset = "GBK";

    @PostConstruct
    public void init() {
        Assert.notNull(this.paternerKey, "������paternerKey");
        Assert.notNull(this.partnerId, "������partnerId");

        Assert.notNull(this.notifyUrl, "������notifyUrl");

        Assert.notNull(this.appId, "������appId");
        Assert.notNull(this.paySignkey, "������paySignkey");
        Assert.notNull(this.charset, "������charset");
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

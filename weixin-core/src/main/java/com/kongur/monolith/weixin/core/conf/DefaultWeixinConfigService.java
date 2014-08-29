package com.kongur.monolith.weixin.core.conf;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * ΢�����÷���
 * 
 * @author zhengwei
 */
@Service("weixinConfigService")
public class DefaultWeixinConfigService implements WeixinConfigService {

    /**
     * ΢�Ź��ں�appid
     */
    @Value("${weixin.appId}")
    private String appId;

    /**
     * �Ƹ�ͨ�̻�Ȩ����ԿKey
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

        Assert.notNull(this.appId, "������appId");
        Assert.notNull(this.paternerKey, "������paternerKey");
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

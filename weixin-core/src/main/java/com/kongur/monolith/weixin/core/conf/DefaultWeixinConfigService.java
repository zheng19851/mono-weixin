package com.kongur.monolith.weixin.core.conf;

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
    @Value("weixin.appId")
    private String appId;

    @PostConstruct
    public void init() {

        Assert.notNull(this.appId, "请设置appId");
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}

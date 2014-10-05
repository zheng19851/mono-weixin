package com.runssnail.monolith.weixin.core.conf;

/**
 * 微信配置服务
 * 
 * @author zhengwei
 */
public interface WeixinConfigService {

    /**
     * 财付通商户权限密钥Key
     * 
     * @return
     */
    String getPaternerKey();

    /**
     * 微信公众号appid
     * 
     * @return
     */
    String getAppId();

    /**
     * 微信公众号appSecret
     * 
     * @return
     */
    String getAppSecret();

    /**
     * token
     * 
     * @return
     */
    String getToken();

    /**
     * 判断传入的appid是否默认的appid
     * 
     * @param appId
     * @return
     */
    boolean isDefaultAppId(String appId);
}

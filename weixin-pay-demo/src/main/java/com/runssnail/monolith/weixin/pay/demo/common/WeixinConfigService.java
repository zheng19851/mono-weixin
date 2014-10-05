package com.runssnail.monolith.weixin.pay.demo.common;

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
     * 接收微信通知的url
     * 
     * @return
     */
    String getNotifyUrl();

    /**
     * 财付通id
     * 
     * @return
     */
    String getPartnerId();

    /**
     * 微信默认字符集
     * 
     * @return
     */
    String getCharset();

    /**
     * 微信公众号appid
     * 
     * @return
     */
    String getAppId();

    /**
     * 微信支付密钥
     * 
     * @return
     */
    String getPaySignkey();
}

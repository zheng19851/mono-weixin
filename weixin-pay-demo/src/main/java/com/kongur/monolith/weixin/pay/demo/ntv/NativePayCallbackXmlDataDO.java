package com.kongur.monolith.weixin.pay.demo.ntv;

import com.kongur.monolith.common.DomainBase;

/**
 * native api回调商户post数据
 * 
 * @author zhengwei
 */
public class NativePayCallbackXmlDataDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 540646586823580297L;

    /**
     * 点击链接准备购买商品的用户标识
     */
    private String            OpenId;

    /**
     * 公众帐号的appid
     */
    private String            AppId;

    /**
     * 标记用户是否订阅该公众帐号，1 为关注，0 为未关注
     */
    private String            IsSubscribe;

    /**
     * 第三方的商品ID 号
     */
    private String            ProductId;

    /**
     * 时间戳
     */
    private String            TimeStamp;

    /**
     * 随机串
     */
    private String            NonceStr;

    /**
     * 参数的加密签名
     */
    private String            AppSignature;

    /**
     * 签名方式，目前只支持“SHA1”，该字段不参与签名
     */
    private String            SignMethod;

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String openId) {
        OpenId = openId;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getIsSubscribe() {
        return IsSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        IsSubscribe = isSubscribe;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getNonceStr() {
        return NonceStr;
    }

    public void setNonceStr(String nonceStr) {
        NonceStr = nonceStr;
    }

    public String getAppSignature() {
        return AppSignature;
    }

    public void setAppSignature(String appSignature) {
        AppSignature = appSignature;
    }

    public String getSignMethod() {
        return SignMethod;
    }

    public void setSignMethod(String signMethod) {
        SignMethod = signMethod;
    }

}

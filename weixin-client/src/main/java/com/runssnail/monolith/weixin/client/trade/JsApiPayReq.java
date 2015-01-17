package com.runssnail.monolith.weixin.client.trade;

import com.runssnail.monolith.common.DomainBase;

/**
 * js api 支付请求数据
 * 
 * @author zhengwei
 */
public class JsApiPayReq extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -5033942029745100717L;

    /**
     * 微信公众号id
     */
    private String            appId;

    /**
     * 微信预支付单id
     */
    private String            prepayId;

    /**
     * 微信支付密钥
     */
    private String            paySignKey;

    /**
     * 随机字符串
     */
    private String            nonceStr;

    /**
     * 时间戳
     */
    private long              timeStamp;

    /**
     * 支付签名
     */
    private String            paySgin;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPaySignKey() {
        return paySignKey;
    }

    public void setPaySignKey(String paySignKey) {
        this.paySignKey = paySignKey;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPaySgin() {
        return paySgin;
    }

    public void setPaySgin(String paySgin) {
        this.paySgin = paySgin;
    }

}

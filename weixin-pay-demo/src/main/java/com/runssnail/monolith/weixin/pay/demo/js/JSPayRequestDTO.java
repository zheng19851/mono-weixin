package com.runssnail.monolith.weixin.pay.demo.js;

import com.runssnail.monolith.common.DomainBase;
import com.runssnail.monolith.weixin.pay.demo.common.EnumSignType;

/**
 * 付款请求对象，包含了付款请求需要用到的数据
 * 
 * @author zhengwei
 */
public class JSPayRequestDTO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 7738340076895191921L;
    /**
     * 公众号id
     */
    private String            appId;
    /**
     * 随机字符串
     */
    private String            nonceStr;
    private String            packageStr;

    /**
     * 时间戳
     */
    private String            timestamp;

    /**
     * 支付签名
     */
    private String            paySign;

    /**
     * 签名方式, 目前仅支持SHA1
     */
    private String            signType         = EnumSignType.SHA1.getVal();

    public JSPayRequestDTO(String appId, String nonceStr, String timestamp, String packageStr, String paySign) {
        this.appId = appId;
        this.nonceStr = nonceStr;
        this.packageStr = packageStr;
        this.paySign = paySign;
        this.timestamp = timestamp;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

}

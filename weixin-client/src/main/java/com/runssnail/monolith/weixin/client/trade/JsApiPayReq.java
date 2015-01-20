package com.runssnail.monolith.weixin.client.trade;

import com.runssnail.monolith.common.DomainBase;

/**
 * js api ֧����������
 * 
 * @author zhengwei
 */
public class JsApiPayReq extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -5033942029745100717L;

    /**
     * ΢�Ź��ں�id
     */
    private String            appId;

    /**
     * ΢��Ԥ֧����id
     */
    private String            prepayId;

    /**
     * ����ַ���
     */
    private String            nonceStr;

    /**
     * ʱ���
     */
    private long              timeStamp;

    /**
     * ֧��ǩ��
     */
    private String            paySign;

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

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

}

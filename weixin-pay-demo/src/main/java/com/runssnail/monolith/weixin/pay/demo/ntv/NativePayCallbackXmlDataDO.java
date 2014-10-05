package com.runssnail.monolith.weixin.pay.demo.ntv;

import com.runssnail.monolith.common.DomainBase;

/**
 * native api�ص��̻�post����
 * 
 * @author zhengwei
 */
public class NativePayCallbackXmlDataDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 540646586823580297L;

    /**
     * �������׼��������Ʒ���û���ʶ
     */
    private String            OpenId;

    /**
     * �����ʺŵ�appid
     */
    private String            AppId;

    /**
     * ����û��Ƿ��ĸù����ʺţ�1 Ϊ��ע��0 Ϊδ��ע
     */
    private String            IsSubscribe;

    /**
     * ����������ƷID ��
     */
    private String            ProductId;

    /**
     * ʱ���
     */
    private String            TimeStamp;

    /**
     * �����
     */
    private String            NonceStr;

    /**
     * �����ļ���ǩ��
     */
    private String            AppSignature;

    /**
     * ǩ����ʽ��Ŀǰֻ֧�֡�SHA1�������ֶβ�����ǩ��
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

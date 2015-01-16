package com.runssnail.monolith.weixin.client.trade;

import java.util.Date;

import com.runssnail.monolith.common.DomainBase;

/**
 * ���ײ���
 * 
 * @author zhengwei
 */
public class TradeDTO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 4047901802674891486L;

    /**
     * ��Ʒ����������
     */
    private String            productDesc;

    /**
     * ϵͳ�����ţ�����
     */
    private String            orderId;

    /**
     * �ܽ�����
     */
    private Long              totalFee;

    /**
     * �������ɵĻ���IP������
     */
    private String            ip;

    /**
     * ����΢��֧���ɹ�֪ͨ������
     */
    private String            notifyUrl;

    /**
     * JSAPI��NATIVE��APP������
     */
    private EnumTradeType     tradeType;

    /**
     * �û����̻�appid�µ�Ψһ��ʶ��trade_typeΪJSAPIʱ���ش�
     */
    private String            openId;

    // ================================����Ĳ����Ǳ���===========================================

    /**
     * ֻ��trade_typeΪNATIVEʱ��Ҫ��д����idΪ��ά���а�������ƷID
     */
    private String            productId;

    /**
     * ΢��֧��������ն��豸�ţ� �Ǳ���
     */
    private String            deviceInfo;

    /**
     * �������ݣ��Ǳ���
     */
    private String            attach;

    /**
     * ��������ʱ�䣬�Ǳ���
     * <p>
     * ��ʽΪyyyyMMddHHmmss����2009��12��25��9��10��10���ʾΪ20091225091010��ʱ��ΪGMT+8 beijing����ʱ��ȡ���̻�������
     */
    private Date              createTime;

    /**
     * ����ʧЧʱ�䣬�Ǳ���
     * <p>
     * ��ʽΪyyyyMMddHHmmss����2009��12��27��9��10��10���ʾΪ20091227091010��ʱ��ΪGMT+8 beijing����ʱ��ȡ���̻�������
     */
    private Date              expireTime;

    /**
     * ��Ʒ��ǣ����ֶβ��������Ǳ���
     */
    private String            goodsTag;

    public EnumTradeType getTradeType() {
        return tradeType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setTradeType(EnumTradeType type) {
        this.tradeType = type;
    }

}

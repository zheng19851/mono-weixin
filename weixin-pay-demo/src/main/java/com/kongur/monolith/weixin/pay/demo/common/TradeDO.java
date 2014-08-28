package com.kongur.monolith.weixin.pay.demo.common;

import com.kongur.monolith.common.DomainBase;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * ��������
 * 
 * @author zhengwei
 */
public class TradeDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -7344983560942657861L;

    /**
     * ����ͨ����
     */
    @NotNull
    private String bankType     = "WX";

    /**
     * ��Ʒ�������ַ������ͣ�128 �ֽ�����
     */
    @NotNull
    private String productDesc;

    /**
     * �������ݣ�128 �ֽ�����
     */
    @Nullable
    private String attach;

    /**
     * �̻������ţ�32 �ֽ�����
     */
    @NotNull
    private String tradeId;

    /**
     * �����ܽ��, ��λ�֣� �뱣֤ transport_fee + product_fee=total_fee��
     */
    @NotNull
    private long   totalFee;

    /**
     * ֧������
     */
    @NotNull
    private String feeType      = "1";

    /**
     * ������ʼʱ�䣬14 �ֽ�����
     */
    @Nullable
    private String timeStart;

    /**
     * ���׽���ʱ�䣬14 �ֽ�����
     */
    @Nullable
    private String timeExpire;

    /**
     * ������, ��λΪ��
     */
    @Nullable
    private Long   transportFee;

    /**
     * ��Ʒ����, ��λΪ��
     */
    @Nullable
    private Long   productFee;

    /**
     * ��Ʒ��
     */
    @Nullable
    private String goodsTag;

    /**
     * ��������ַ����룬ȡֵ��Χ��"GBK"��"UTF-8"��Ĭ�ϣ�"GBK"
     */
    @NotNull
    private String inputCharset = "GBK";

    /**
     * �û��������IP�������̻�������IP����ʽΪIPV4��
     */
    @NotNull
    private String userIp;

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public Long getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(Long transportFee) {
        this.transportFee = transportFee;
    }

    public Long getProductFee() {
        return productFee;
    }

    public void setProductFee(Long productFee) {
        this.productFee = productFee;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

}

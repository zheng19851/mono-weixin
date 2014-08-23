package com.kongur.monolith.weixin.pay.demo.common;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * 交易数据
 * 
 * @author zhengwei
 */
public class TradeDO {

    /**
     * 银行通道类
     */
    @NotNull
    private String bankType     = "WX";

    /**
     * 商品描述
     */
    @NotNull
    private String productDesc;

    /**
     * 附加数据
     */
    @Nullable
    private String attach;

    /**
     * 商户订单号
     */
    @NotNull
    private String tradeId;

    /**
     * 订单总金额, 单位分， 须保证 transport_fee + product_fee=total_fee；
     */
    @NotNull
    private long   totalFee;

    /**
     * 支付币种
     */
    @NotNull
    private String feeType      = "1";

    /**
     * 交易起始时间
     */
    @Nullable
    private String timeStart;

    /**
     * 交易结束时间
     */
    @Nullable
    private String timeExpire;

    /**
     * 物流费, 单位为分
     */
    @Nullable
    private Long   transportFee;

    /**
     * 商品费用, 单位为分
     */
    @Nullable
    private Long   productFee;

    /**
     * 商品标
     */
    @Nullable
    private String goodsTag;

    /**
     * 传入参数字符编码，取值范围："GBK"、"UTF-8"，默认："GBK"
     */
    @NotNull
    private String inputCharset = "GBK";

    /**
     * 用户浏览器端IP，不是商户服务器IP，格式为IPV4；
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

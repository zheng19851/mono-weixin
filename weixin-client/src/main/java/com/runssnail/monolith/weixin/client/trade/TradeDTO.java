package com.runssnail.monolith.weixin.client.trade;

import java.util.Date;

import com.runssnail.monolith.common.DomainBase;

/**
 * 交易参数
 * 
 * @author zhengwei
 */
public class TradeDTO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 4047901802674891486L;

    /**
     * 商品描述，必填
     */
    private String            productDesc;

    /**
     * 系统订单号，必填
     */
    private String            orderId;

    /**
     * 总金额，必填
     */
    private Long              totalFee;

    /**
     * 订单生成的机器IP，必填
     */
    private String            ip;

    /**
     * 接收微信支付成功通知，必填
     */
    private String            notifyUrl;

    /**
     * JSAPI、NATIVE、APP，必填
     */
    private EnumTradeType     tradeType;

    /**
     * 用户在商户appid下的唯一标识，trade_type为JSAPI时，必传
     */
    private String            openId;

    // ================================下面的参数非必填===========================================

    /**
     * 只在trade_type为NATIVE时需要填写，此id为二维码中包含的商品ID
     */
    private String            productId;

    /**
     * 微信支付分配的终端设备号， 非必填
     */
    private String            deviceInfo;

    /**
     * 附加数据，非必填
     */
    private String            attach;

    /**
     * 订单生成时间，非必填
     * <p>
     * 格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    private Date              createTime;

    /**
     * 订单失效时间，非必填
     * <p>
     * 格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。时区为GMT+8 beijing。该时间取自商户服务器
     */
    private Date              expireTime;

    /**
     * 商品标记，该字段不能随便填，非必填
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

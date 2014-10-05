package com.runssnail.monolith.weixin.client.refund;

import com.runssnail.monolith.common.DomainBase;

/**
 * 退款申请数据对象
 * 
 * @author zhengwei
 */
public class RefundApply extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -8949545311970066822L;

    /**
     * 商户号 partner 是 String(10) 商户号,由财付通统一分配的10 位正整数 (120XXXXXXX)号
     */
    private String            partner;

    /**
     * 商户订单号 out_trade_no 否 String(32) 商户系统内部的订单号, out_trade_no 和 transaction_id、out_refund_no、refund_id
     * 至少一个必填，同时存在时以优先级高为准， 优先级为： refund_id>out_refund_no>transaction_id> out_trade_no
     */
    private String            outTradeNo;

    /**
     * 财付通订单号 transaction_id 否 String(28) 财付通订单号
     */
    private String            transactionId;

    /**
     * 商户退款单号 out_refund_no 否 String(32) 商户退款单号
     */
    private String            outRefundNo;

    /**
     * 总金额 total_fee 是 Int 订单总金额，单位为分
     */
    private Long              totalFee;

    /**
     * 退款金额 refund_fee 是 Int 退款总金额,单位为分,可以做部分退款
     */
    private Long              refundFee;

    /**
     * 操作员 op_user_id 是 Int 操作员帐号,默认为商户号
     */
    private Long              opUserId;

    /**
     * 操作员密码 op_user_passwd 是 String(32) 操作员密码,默认为商户后台登录密码 version 为1.0 时，密码为明文 version 为1.1 时，密码为MD5(密码)值
     */
    private String            opUserPasswd;

    /**
     * 接收人帐号 recv_user_id 否 String(64) 转账退款接收退款的财付通帐号。 一般无需填写，只有退银行失败，资金转入商 户号现金账号时（即状态为转入代发，查询返 回的 refund_status 是7
     * 或11），填写原退款 单号并填写此字段，资金才会退到指定财付通 账号。其他情况此字段忽略
     */
    private String            recvUserId;

    /**
     * 接收人姓名 reccv_user_name 否 String(32) 转账退款接收退款的姓名(需与接收退款的财 付通帐号绑定的姓名一致)
     */
    private String            reccvUserName;

    /**
     * 通过商户订单 号退款 use_spbill_no_fl ag 否 Int 若通过接口 (https://www.tenpay.com/cgi-bin/v1.0/pay _gate.cgi) 支付的商户订单号来退款，则取值
     * 为1；而通过本文档支付接口的，则无需传值。
     */
    private Integer           useSpbillNoFl;

    /**
     * 退款类型 refund_type 否 Int 为空或者填1:商户号余额退款；2：现金帐号 退款； 3:优先商户号退款，若商户号余额不足， 再做现金帐号退款。使用2 或3 时，需联系财 付通开通此功能。
     */
    private Integer           refundType;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }

    public Long getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Long opUserId) {
        this.opUserId = opUserId;
    }

    public String getOpUserPasswd() {
        return opUserPasswd;
    }

    public void setOpUserPasswd(String opUserPasswd) {
        this.opUserPasswd = opUserPasswd;
    }

    public String getRecvUserId() {
        return recvUserId;
    }

    public void setRecvUserId(String recvUserId) {
        this.recvUserId = recvUserId;
    }

    public String getReccvUserName() {
        return reccvUserName;
    }

    public void setReccvUserName(String reccvUserName) {
        this.reccvUserName = reccvUserName;
    }

    public Integer getUseSpbillNoFl() {
        return useSpbillNoFl;
    }

    public void setUseSpbillNoFl(Integer useSpbillNoFl) {
        this.useSpbillNoFl = useSpbillNoFl;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

}

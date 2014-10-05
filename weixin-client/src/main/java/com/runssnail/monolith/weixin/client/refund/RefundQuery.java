package com.runssnail.monolith.weixin.client.refund;

import com.runssnail.monolith.common.DomainBase;

/**
 * 退款单查询
 * 
 * @author zhengwei
 */
public class RefundQuery extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -7111886599383865633L;

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
     * 财付通退款单号 refund_id 否 String(28) 财付通退款单号
     */
    private String            refundId;

    /**
     * 通过商户订单 号退款 use_spbill_no_fl ag 否 Int 若通过接口 (https://www.tenpay.com/cgi-bin/v1.0/pay _gate.cgi) 支付的商户订单号来退款，则取值
     * 为1；而通过本文档支付接口的，则无需传值。
     */
    private Integer           useSpbillNoFl;

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

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Integer getUseSpbillNoFl() {
        return useSpbillNoFl;
    }

    public void setUseSpbillNoFl(Integer useSpbillNoFl) {
        this.useSpbillNoFl = useSpbillNoFl;
    }

}

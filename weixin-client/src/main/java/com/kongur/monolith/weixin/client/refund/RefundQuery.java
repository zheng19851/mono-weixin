package com.kongur.monolith.weixin.client.refund;

import com.kongur.monolith.common.DomainBase;

/**
 * 退款单查询
 * 
 * @author zhengwei
 *
 */
public class RefundQuery extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -7111886599383865633L;

    // 商户号 partner
    // 是 String(10) 商户号,由财付通统一分配的10 位正整数
    // (120XXXXXXX)号
    // 商户订单号 out_trade_no
    // 否 String(32) 商户系统内部的订单号, out_trade_no 和
    // transaction_id、out_refund_no、refund_id
    // 至少一个必填，同时存在时以优先级高为准，
    // 15
    // 优先级为：
    // refund_id>out_refund_no>transaction_id>
    // out_trade_no
    // 财付通订单号 transaction_id
    // 否 String(28) 财付通订单号, out_trade_no 和
    // transaction_id、out_refund_no、refund_id
    // 至少一个必填，同时存在时以优先级高为准，
    // 优先级为：
    // refund_id>out_refund_no>transaction_id>
    // out_trade_no
    // 商户退款单号 out_refund_no
    // 否 String(32) 商户退款单号, out_trade_no 和
    // transaction_id、out_refund_no、refund_id
    // 至少一个必填，同时存在时以优先级高为准，
    // 优先级为：
    // refund_id>out_refund_no>transaction_id>
    // out_trade_no
    // 财付通退款单
    // 号
    // refund_id
    // 否 String(28) 财付通退款单号, out_trade_no 和
    // transaction_id、out_refund_no、refund_id
    // 至少一个必填，同时存在时以优先级高为准，
    // 优先级为：
    // refund_id>out_refund_no>transaction_id>
    // out_trade_no
    // 通过商户订单
    // 号退款查询
    // use_spbill_no_fl
    // ag
    // 否 Int 若通过接口
    // (https://www.tenpay.com/cgi-bin/v1.0/pay
    // _gate.cgi) 支付的商户订单号来退款，则取值
    // 为1；而通过本文档支付接口的，则无需传值
}

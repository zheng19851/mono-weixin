package com.kongur.monolith.weixin.client.refund;

import com.kongur.monolith.common.DomainBase;

/**
 * 退款结果
 * 
 * @author zhengwei
 *
 */
public class RefundInfo extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -4128306298346612361L;

    
//    商户号 partner 是 String(10) 商户号
//    财付通订单号 transaction_id
//    是 String(28) 财付通交易号，28 位长的数值，其中前10 位
//    为商户号，之后8 位为订单产生的日期，如
//    20090415，最后10 位是流水号。
//    商户订单号 out_trade_no 是 String(32) 商户系统内部的订单号
//    商户退款单号 out_refund_no 是 String(32) 商户退款单号
//    财付通退款单
//    号
//    refund_id
//    是 String(28)
//    财付通退款单号
//    退款渠道 refund_channel 是 Int 退款渠道,0:退到财付通、1:退到银行
//    退款金额 refund_fee 是 Int 退款总金额,单位为分,可以做部分退款
//    退款状态 refund_status
//    是 Int 退款状态：
//    4，10：退款成功。
//    3，5，6：退款失败。
//    8，9，11：退款处理中。
//    1，2：未确定，需要商户原退款单号重新发起。
//    7：转入代发，退款到银行发现用户的卡作废或
//    者冻结了，导致原路退款银行卡失败，资金回
//    流到商户的现金帐号，需要商户人工干预，通
//    14
//    过线下或者财付通转账的方式进行退款。
//    接收人帐号 recv_user_id 否 String(64) 转账退款接收退款的财付通帐号
//    接收人姓名 reccv_user_name 否 String(32) 转账退款接收退款的姓名(需与接收退款的财
//    付通帐号绑定的姓名一致)
}

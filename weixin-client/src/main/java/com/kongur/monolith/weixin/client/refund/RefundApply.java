package com.kongur.monolith.weixin.client.refund;

import com.kongur.monolith.common.DomainBase;

/**
 * 退款申请数据对象
 * 
 * @author zhengwei
 *
 */
public class RefundApply extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -8949545311970066822L;

//    商户号 partner
//    是 String(10) 商户号,由财付通统一分配的10 位正整数
//    (120XXXXXXX)号
//    商户订单号 out_trade_no
//    否 String(32) 商户系统内部的订单号, out_trade_no 和
//    transaction_id 至少一个必填，同时存在时
//    transaction_id 优先
//    财付通订单号 transaction_id
//    否 String(28) 财付通交易号, out_trade_no 和
//    transaction_id 至少一个必填，同时存在时
//transaction_id 优先
//商户退款单号 out_refund_no
//是 String(32) 商户退款单号，32 个字符内、可包含字母,确
//保在商户系统唯一。同个退款单号多次请求，
//财付通当一个单处理，只会退一次款。如果出
//现退款不成功，请采用原退款单号重新发起，
//避免出现重复退款。
//总金额 total_fee 是 Int 订单总金额，单位为分
//退款金额 refund_fee 是 Int 退款总金额,单位为分,可以做部分退款
//操作员 op_user_id 是 Int 操作员帐号,默认为商户号
//操作员密码 op_user_passwd 是 String(32) 操作员密码,默认为商户后台登录密码
//version 为1.0 时，密码为明文
//version 为1.1 时，密码为MD5(密码)值
//接收人帐号 recv_user_id 否 String(64) 转账退款接收退款的财付通帐号。
//一般无需填写，只有退银行失败，资金转入商
//户号现金账号时（即状态为转入代发，查询返
//回的refund_status 是7 或11），填写原退款
//单号并填写此字段，资金才会退到指定财付通
//账号。其他情况此字段忽略
//接收人姓名 reccv_user_name 否 String(32) 转账退款接收退款的姓名(需与接收退款的财
//付通帐号绑定的姓名一致)
//通过商户订单
//号退款
//use_spbill_no_fl
//ag
//否 Int 若通过接口
//(https://www.tenpay.com/cgi-bin/v1.0/pay
//_gate.cgi) 支付的商户订单号来退款，则取值
//为1；而通过本文档支付接口的，则无需传值。
//退款类型 refund_type 否 Int 为空或者填1:商户号余额退款；2：现金帐号
//退款； 3:优先商户号退款，若商户号余额不足，
//再做现金帐号退款。使用2 或3 时，需联系财
//付通开通此功能。
}

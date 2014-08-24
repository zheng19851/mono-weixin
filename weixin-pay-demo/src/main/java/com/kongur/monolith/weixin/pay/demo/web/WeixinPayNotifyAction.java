package com.kongur.monolith.weixin.pay.demo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 微信支付通知接口
 * 
 * @author zhengwei
 *
 */
@Controller
public class WeixinPayNotifyAction {

    /**
     * 微信支付通知接口
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("cgi/weixin/receive_pay_notify.htm")
    public String notifyReceived(HttpServletRequest req) {
       
//        签名方式sign_type 否String(8) 签名类型，取值：MD5、RSA，默
//        认：MD5
//        字符集input_charset 否String(8) 字符编码,取值：GBK、UTF-8，默
//        认：GBK。
//        签名sign 是String(32) 签名
//        业务参数
//        交易模式trade_mode 是Int 1-即时到账
//        其他保留
//        交易状态trade_state 是Int 支付结果：
//        0—成功
//        其他保留
//        微信公众号支付接口文档V2.7
//        商户号partner 是String(10) 商户号，也即之前步骤的partnerid,
//        由微信统一分配的10 位正整数
//        (120XXXXXXX)号
//        付款银行bank_type 是String(16) 银行类型，在微信中使用WX
//        银行订单号bank_billno 否String(32) 银行订单号
//        总金额total_fee 是Int 支付金额，单位为分，如果discount
//        有值，通知的total_fee + discount =
//        请求的total_fee
//        币种fee_type 是Int 现金支付币种,目前只支持人民币,
//        默认值是1-人民币
//        通知ID notify_id 是String(128) 支付结果通知id，对于某些特定商
//        户，只返回通知id，要求商户据此
//        查询交易结果
//        订单号transaction_id 是String(28) 交易号，28 位长的数值，其中前10
//        位为商户号，之后8 位为订单产生
//        的日期，如20090415，最后10 位
//        是流水号。
//        商户订单号out_trade_no 是String(32) 商户系统的订单号，与请求一致。
//        商户数据包attach 否String(127) 商户数据包，原样返回，空参数不
//        传递
//        支付完成时间time_end 是String(14) 支付完成时间， 格式为
//        yyyyMMddhhmmss，如2009 年12
//        月27 日9 点10 分10 秒表示为
//        20091227091010。时区为GMT+8
//        beijing。
//        物流费用transport_fee 否Int 物流费用，单位分，默认0。如果
//        有值， 必须保证transport_fee +
//        product_fee = total_fee
//        物品费用product_fee 否Int 物品费用，单位分。如果有值，必
//        须保证transport_fee +
//        product_fee=total_fee
//        折扣价格discount 否Int 折扣价格，单位分，如果有值，通
//        知的total_fee + discount = 请求的
//        total_fee
        
        // 解析参数
        
        // 校验数据签名
        
        // 直接返回成功，并发送事件

         // 接受事件去处理订单状态
        
        
        return "success";
    }
    
}

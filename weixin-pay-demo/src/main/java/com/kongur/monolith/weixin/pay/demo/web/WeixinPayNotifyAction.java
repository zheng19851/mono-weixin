package com.kongur.monolith.weixin.pay.demo.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.pay.demo.common.WeixinPaymentHelper;

/**
 * 微信支付通知接口
 * 
 * @author zhengwei
 */
@Controller
public class WeixinPayNotifyAction {

    private final Logger        log = Logger.getLogger(getClass());

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;
    
    private static final String FAIL = "fail";
    
    private static final String SUCCESS = "success";

    /**
     * 微信支付通知接口
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("cgi/weixin/receive_pay_notify.htm")
    public String notifyReceived(HttpServletRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("receive pay notify, params=" + req.getParameterMap());
        }

        // 签名方式sign_type 否String(8) 签名类型，取值：MD5、RSA，默
        // 认：MD5
        String signType = req.getParameter("sign_type");
        // 签名sign 是String(32) 签名
        String sign = req.getParameter("sign");

        // 解析参于校验的参数
        SortedMap<String, String> packageParams = buildPackageParams(req);
        
        // 校验数据签名
        String packageSign = weixinPaymentHelper.buildPackageSign(packageParams);
        if (!sign.equals(packageSign)) {
            log.error("receive pay notify error, the sign is not equals. outSign=" + sign + ", build sign="
                      + packageSign);
            return FAIL;
        }

        // post data
        String postData = readPostData(req);
        if (StringUtil.isBlank(postData)) {
            return FAIL;
        }

        // 校验post data ? 暂时不校验也是可以的

        // 修改订单状态为付款成功
        
        // 修改订单状态成功后，向系统发送事件？

        return SUCCESS;
    }

    private String readPostData(HttpServletRequest req) {
        String xmlDataStr = null;

        // 字符集input_charset 否String(8) 字符编码,取值：GBK、UTF-8，默
        // 认：GBK。
        String input_charset = req.getParameter("input_charset");

        InputStream in = null;
        try {
            in = req.getInputStream();
            int len = req.getContentLength();
            byte[] xmlData = new byte[len];
            in.read(xmlData);
            xmlDataStr = new String(xmlData, StringUtil.isBlank(input_charset) ? "GBK" : input_charset);
        } catch (IOException e) {
            log.error("read weixin notify post data error, reqParams=" + req.getParameterMap(), e);
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return xmlDataStr;
    }

    private SortedMap<String, String> buildPackageParams(HttpServletRequest req) {

        String input_charset = req.getParameter("input_charset");

        // 业务参数
        // 交易模式trade_mode 是Int 1-即时到账
        // 其他保留
        String trade_mode = req.getParameter("trade_mode");

        // 交易状态trade_state 是Int 支付结果：
        // 0—成功
        // 其他保留
        String trade_state = req.getParameter("trade_state");
        // 微信公众号支付接口文档V2.7
        // 商户号partner 是String(10) 商户号，也即之前步骤的partnerid,
        // 由微信统一分配的10 位正整数
        // (120XXXXXXX)号
        String partner = req.getParameter("partner");
        // 付款银行bank_type 是String(16) 银行类型，在微信中使用WX
        String bank_type = req.getParameter("bank_type");
        // 银行订单号bank_billno 否String(32) 银行订单号
        String bank_billno = req.getParameter("bank_billno");
        // 总金额total_fee 是Int 支付金额，单位为分，如果discount
        // 有值，通知的total_fee + discount =
        // 请求的total_fee
        String total_fee = req.getParameter("total_fee");
        // 币种fee_type 是Int 现金支付币种,目前只支持人民币,
        // 默认值是1-人民币
        String fee_type = req.getParameter("fee_type");
        // 通知ID notify_id 是String(128) 支付结果通知id，对于某些特定商
        // 户，只返回通知id，要求商户据此
        // 查询交易结果
        String notify_id = req.getParameter("notify_id");
        // 订单号transaction_id 是String(28) 交易号，28 位长的数值，其中前10
        // 位为商户号，之后8 位为订单产生
        // 的日期，如20090415，最后10 位
        // 是流水号。
        String transaction_id = req.getParameter("transaction_id");
        // 商户订单号out_trade_no 是String(32) 商户系统的订单号，与请求一致。
        String out_trade_no = req.getParameter("out_trade_no");
        // 商户数据包attach 否String(127) 商户数据包，原样返回，空参数不
        // 传递
        String attach = req.getParameter("attach");
        // 支付完成时间time_end 是String(14) 支付完成时间， 格式为
        // yyyyMMddhhmmss，如2009 年12
        // 月27 日9 点10 分10 秒表示为
        // 20091227091010。时区为GMT+8
        // beijing。
        String time_end = req.getParameter("time_end");
        // 物流费用transport_fee 否Int 物流费用，单位分，默认0。如果
        // 有值， 必须保证transport_fee +
        // product_fee = total_fee
        String transport_fee = req.getParameter("transport_fee");
        // 物品费用product_fee 否Int 物品费用，单位分。如果有值，必
        // 须保证transport_fee +
        // product_fee=total_fee
        String product_fee = req.getParameter("product_fee");
        // 折扣价格discount 否Int 折扣价格，单位分，如果有值，通
        // 知的total_fee + discount = 请求的
        // total_fee
        String discount = req.getParameter("discount");

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        if (StringUtil.isNotBlank(input_charset)) {
            packageParams.put("input_charset", input_charset);

        }
        packageParams.put("trade_mode", trade_mode);
        packageParams.put("trade_state", trade_state);
        packageParams.put("partner", partner);
        packageParams.put("bank_type", bank_type);
        if (StringUtil.isNotBlank(bank_billno)) {
            packageParams.put("bank_billno", bank_billno);

        }
        packageParams.put("total_fee", total_fee);
        packageParams.put("fee_type", fee_type);
        packageParams.put("notify_id", notify_id);
        packageParams.put("transaction_id", transaction_id);
        packageParams.put("out_trade_no", out_trade_no);
        if (StringUtil.isNotBlank(attach)) {

            packageParams.put("attach", attach);
        }
        packageParams.put("time_end", time_end);

        if (StringUtil.isNotBlank(transport_fee)) {
            packageParams.put("transport_fee", transport_fee);
        }

        if (StringUtil.isNotBlank(product_fee)) {
            packageParams.put("product_fee", product_fee);
        }

        if (StringUtil.isNotBlank(discount)) {
            packageParams.put("discount", discount);
        }
        return packageParams;
    }

}

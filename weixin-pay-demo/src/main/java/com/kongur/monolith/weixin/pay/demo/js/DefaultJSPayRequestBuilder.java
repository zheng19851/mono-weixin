package com.kongur.monolith.weixin.pay.demo.js;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kongur.monolith.weixin.pay.demo.common.DefaultWeixinPaymentHelper;
import com.kongur.monolith.weixin.pay.demo.common.TradeDO;
import com.kongur.monolith.weixin.pay.demo.common.WeixinPaymentHelper;

/**
 * @author zhengwei
 */
@Service("jsPayRequestBuilder")
public class DefaultJSPayRequestBuilder implements JSPayRequestBuilder {

    private final Logger        log     = Logger.getLogger(getClass());

    private final String        charset = "GBK";

    private String              appId;

    /**
     * 付款密钥
     */
    private String              paySignkey;

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    public void init() {
        Assert.notNull(this.appId, "请设置appId");
        Assert.notNull(this.paySignkey, "请设置paySignkey");

        Assert.notNull(this.charset, "请设置charset");
    }

    public JSPayRequestDTO buildPayRequest(TradeDO trade) {

        if (log.isDebugEnabled()) {
            log.debug("createPayRequestDTO, trade data=" + trade);
        }
        // 校验数据 trade

        if (isBlank(trade.getInputCharset())) {
            trade.setInputCharset(this.charset);
        }
        String appId = this.appId;
        String nonceStr = weixinPaymentHelper.buildNonceStr(trade.getInputCharset());
        System.out.println("nonceStr=" + nonceStr);
        String timestamp = String.valueOf(System.currentTimeMillis());
        System.out.println("timestamp=" + timestamp);
        String packageStr = weixinPaymentHelper.buildPackage(trade);

        String paySign = genPaySign(nonceStr, timestamp, packageStr);
        JSPayRequestDTO payReq = new JSPayRequestDTO(appId, nonceStr, timestamp, packageStr, paySign);

        return payReq;
    }

    private boolean isBlank(String str) {
        return !isNotBlank(str);
    }

    private boolean isNotBlank(String str) {
        return str != null && str != "";
    }

    /**
     * 付款签名
     * 
     * @param packageStr
     * @param timestamp
     * @param nonceStr
     * @return
     * @throws Exception
     */
    private String genPaySign(String nonceStr, String timestamp, String packageStr) {
        if (log.isDebugEnabled()) {
            log.debug("genPaySign, nonceStr=" + nonceStr + ", timestamp=" + timestamp + ", packageStr=" + packageStr);
        }

        // 设置支付参数
        SortedMap<String, String> signParams = new TreeMap<String, String>();
        signParams.put("appid", this.appId);
        signParams.put("nonceStr", nonceStr);
        signParams.put("package", packageStr);
        signParams.put("timestamp", timestamp);
        signParams.put("appkey", this.paySignkey);

        String sign = weixinPaymentHelper.buildPaySign(signParams);

        return sign;
    }

    public static void main(String[] args) {
        TradeDO trade = new TradeDO();
        trade.setInputCharset("UTF-8");
        trade.setProductDesc("支付测试");
        trade.setTradeId("7240b65810859cbf2a8d9f76a638c0a3");
        trade.setTotalFee(1L);
        trade.setUserIp("196.168.1.1");

        DefaultJSPayRequestBuilder builder = new DefaultJSPayRequestBuilder();

        DefaultWeixinPaymentHelper helper = new DefaultWeixinPaymentHelper();
        helper.setPaternerKey("8934e7d15453e97507ef794cf7b0519d");

        builder.weixinPaymentHelper = helper;

        helper.setPartnerId("1900000109");
        helper.setNotifyUrl("http://weixin.qq.com");
        // String packageStr = builder.genPackage(trade);
        // builder.genPaySign(nonceStr, timestamp, packageStr);
        builder.buildPayRequest(trade);

        // System.out.println(packageStr);

    }

}

package com.runssnail.monolith.weixin.pay.demo.test;

import com.runssnail.monolith.weixin.pay.demo.common.DefaultWeixinConfigService;
import com.runssnail.monolith.weixin.pay.demo.common.DefaultWeixinPaymentHelper;
import com.runssnail.monolith.weixin.pay.demo.common.TradeDO;
import com.runssnail.monolith.weixin.pay.demo.js.DefaultJSPayRequestBuilder;
import com.runssnail.monolith.weixin.pay.demo.js.JSPayRequestDTO;


public class DefaultWeixinPaymentHelperTest {

    public static void main(String[] args) {
        TradeDO trade = new TradeDO();
        trade.setInputCharset("UTF-8");
        trade.setProductDesc("÷ß∏∂≤‚ ‘");
        trade.setTradeId("7240b65810859cbf2a8d9f76a638c0a3");
        trade.setTotalFee(1L);
        trade.setUserIp("196.168.1.1");

        DefaultWeixinConfigService configService = new DefaultWeixinConfigService();

        configService.setPaternerKey("8934e7d15453e97507ef794cf7b0519d");
        configService.setPartnerId("1900000109");
        configService.setNotifyUrl("http://weixin.qq.com");
        configService.setAppId("test_appid");

        DefaultJSPayRequestBuilder builder = new DefaultJSPayRequestBuilder();
        builder.setWeixinConfigService(configService);

        DefaultWeixinPaymentHelper helper = new DefaultWeixinPaymentHelper();
        helper.setWeixinConfigService(configService);

        builder.setWeixinPaymentHelper(helper);

        // String packageStr = builder.genPackage(trade);
        // builder.genPaySign(nonceStr, timestamp, packageStr);
        JSPayRequestDTO req = builder.buildPayRequest(trade);
        System.out.println("req=" + req);
        // System.out.println(packageStr);

    }

}

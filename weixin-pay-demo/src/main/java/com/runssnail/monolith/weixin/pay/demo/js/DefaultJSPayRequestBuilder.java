package com.runssnail.monolith.weixin.pay.demo.js;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.weixin.pay.demo.common.DefaultWeixinConfigService;
import com.runssnail.monolith.weixin.pay.demo.common.DefaultWeixinPaymentHelper;
import com.runssnail.monolith.weixin.pay.demo.common.TradeDO;
import com.runssnail.monolith.weixin.pay.demo.common.WeixinConfigService;
import com.runssnail.monolith.weixin.pay.demo.common.WeixinPaymentHelper;

/**
 * @author zhengwei
 */
@Service("jsPayRequestBuilder")
public class DefaultJSPayRequestBuilder implements JSPayRequestBuilder {

    private final Logger        log = Logger.getLogger(getClass());

    @Autowired
    private WeixinConfigService weixinConfigService;

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    public void init() {

    }

    public JSPayRequestDTO buildPayRequest(TradeDO trade) {

        if (log.isDebugEnabled()) {
            log.debug("createPayRequestDTO, trade data=" + trade);
        }
        // 校验数据 trade

        if (isBlank(trade.getInputCharset())) {
            trade.setInputCharset(weixinConfigService.getCharset());
        }
        String appId = weixinConfigService.getAppId();
        String nonceStr = weixinPaymentHelper.buildNonceStr(trade.getInputCharset());
//        System.out.println("nonceStr=" + nonceStr);
        String timestamp = String.valueOf(System.currentTimeMillis());
//        System.out.println("timestamp=" + timestamp);
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
        signParams.put("appid", weixinConfigService.getAppId());
        signParams.put("noncestr", nonceStr);
        signParams.put("package", packageStr);
        signParams.put("timestamp", timestamp);
        signParams.put("appkey", weixinConfigService.getPaySignkey());

        String sign = weixinPaymentHelper.buildPaySign(signParams);

        return sign;
    }

    public WeixinConfigService getWeixinConfigService() {
        return weixinConfigService;
    }

    public void setWeixinConfigService(WeixinConfigService weixinConfigService) {
        this.weixinConfigService = weixinConfigService;
    }

    public WeixinPaymentHelper getWeixinPaymentHelper() {
        return weixinPaymentHelper;
    }

    public void setWeixinPaymentHelper(WeixinPaymentHelper weixinPaymentHelper) {
        this.weixinPaymentHelper = weixinPaymentHelper;
    }

    public static void main(String[] args) {
        TradeDO trade = new TradeDO();
        trade.setInputCharset("UTF-8");
        trade.setProductDesc("支付测试");
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

        builder.weixinPaymentHelper = helper;

        // String packageStr = builder.genPackage(trade);
        // builder.genPaySign(nonceStr, timestamp, packageStr);
        JSPayRequestDTO req = builder.buildPayRequest(trade);
        System.out.println("req=" + req);
        // System.out.println(packageStr);

    }

}

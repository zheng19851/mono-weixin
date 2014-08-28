package com.kongur.monolith.weixin.pay.demo.ntv;

import java.text.MessageFormat;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kongur.monolith.weixin.pay.demo.common.WeixinConfigService;
import com.kongur.monolith.weixin.pay.demo.common.WeixinPaymentHelper;

/**
 * 默认的微信原生支付url生成器实现
 * 
 * @author zhengwei
 */
@Service("nativePayUrlBuilder")
public class DefaultNativePayUrlBuilder implements NativePayUrlBuilder {

    private final Logger        log           = Logger.getLogger(getClass());

    /**
     * 微信原生支付url
     */
    @Value("weixin.payment.native.payUrlPattern")
    private String              payUrlPattern = "weixin://wxpay/bizpayurl?sign={0}&appid={1}&productid={2}&timestamp={3}&noncestr={4}";

    @Autowired
    private WeixinConfigService weixinConfigService;

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    @PostConstruct
    public void init() {
        Assert.notNull(this.payUrlPattern, "请设置payUrlPattern");
    }

    @Override
    public String buildPayUrl(String productId) {
        String payUrl = null;
        String timestamp = String.valueOf(System.currentTimeMillis());
        String noncestr = weixinPaymentHelper.buildNonceStr(weixinConfigService.getCharset());
        String sign = buildSign(timestamp, noncestr, productId);
        payUrl = MessageFormat.format(this.payUrlPattern, sign, weixinConfigService.getAppId(), productId, timestamp,
                                      noncestr);
        return payUrl;
    }

    private String buildSign(String timestamp, String noncestr, String productId) {

        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", weixinConfigService.getAppId());
        params.put("timestamp", timestamp);
        params.put("noncestr", noncestr);
        params.put("productid", productId);
        params.put("appkey", weixinConfigService.getPaySignkey());

        if (log.isDebugEnabled()) {
            log.debug("build native pay sign, signParams=" + params);
        }

        String sign = this.weixinPaymentHelper.buildUrlParamsStr(params, weixinConfigService.getCharset());

        if (log.isDebugEnabled()) {
            log.debug("build native pay sign, charset=" + weixinConfigService.getCharset() + ", sign=" + sign);
        }

        return sign;
    }

}

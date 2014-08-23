package com.kongur.monolith.weixin.pay.demo.local;

import java.text.MessageFormat;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kongur.monolith.weixin.pay.demo.common.WeixinPaymentHelper;

/**
 * @author zhengwei
 */
@Service("nativePayUrlBuilder")
public class DefaultNativePayUrlBuilder implements NativePayUrlBuilder {

    private final Logger        log           = Logger.getLogger(getClass());

    private String              appId;

    private String              payUrlPattern = "weixin://wxpay/bizpayurl?sign={0}&appid={1}&productid={2}&timestamp={3}&noncestr={4}";

    /**
     * ∏∂øÓ√‹‘ø
     */
    private String              paySignkey;

    private final String        charset       = "GBK";

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    public void init() {
        Assert.notNull(this.appId, "«Î…Ë÷√appId");
        Assert.notNull(this.payUrlPattern, "«Î…Ë÷√payUrlPattern");
    }

    @Override
    public String buildPayUrl(String productId) {
        String payUrl = null;
        String timestamp = String.valueOf(System.currentTimeMillis());
        String noncestr = weixinPaymentHelper.buildNonceStr(this.charset);
        String sign = buildSign(timestamp, noncestr, productId);
        payUrl = MessageFormat.format(this.payUrlPattern, sign, this.appId, productId, timestamp, noncestr);
        return payUrl;
    }

    private String buildSign(String timestamp, String noncestr, String productId) {

        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", this.appId);
        params.put("timestamp", timestamp);
        params.put("noncestr", noncestr);
        params.put("productid", productId);
        params.put("appkey", this.paySignkey);

        if (log.isDebugEnabled()) {
            log.debug("build native pay sign, signParams=" + params);
        }

        String sign = this.weixinPaymentHelper.buildUrlParamStr(params, this.charset);

        if (log.isDebugEnabled()) {
            log.debug("build native pay sign, charset=" + this.charset + ", sign=" + sign);
        }

        return sign;
    }

}

package com.kongur.monolith.weixin.pay.demo.local;

import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.pay.demo.common.WeixinConfigService;
import com.kongur.monolith.weixin.pay.demo.common.WeixinPaymentHelper;

/**
 * д╛хой╣ож
 * 
 * @author zhengwei
 */
@Service("nativePayCallbackXmlDataValidator")
public class DefaultNativePayCallbackXmlDataValidator implements NativePayCallbackXmlDataValidator {

    @Autowired
    private WeixinConfigService weixinConfigService;

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    @Override
    public boolean validate(NativePayCallbackXmlDataDO xmlData) {

        if (StringUtil.isBlank(xmlData.getAppId()) || StringUtil.isBlank(xmlData.getProductId())
            || StringUtil.isBlank(xmlData.getTimeStamp()) || StringUtil.isBlank(xmlData.getNonceStr())
            || StringUtil.isBlank(xmlData.getOpenId()) || StringUtil.isBlank(xmlData.getIsSubscribe())
            || StringUtil.isBlank(xmlData.getSignMethod())) {

            return false;
        }

        SortedMap<String, String> paramsMap = new TreeMap<String, String>();

        paramsMap.put("appid", xmlData.getAppId());
        paramsMap.put("appkey", weixinConfigService.getPaySignkey());
        paramsMap.put("productid", xmlData.getProductId());
        paramsMap.put("timestamp", xmlData.getTimeStamp());
        paramsMap.put("noncestr", xmlData.getNonceStr());
        paramsMap.put("openid", xmlData.getOpenId());
        paramsMap.put("issubscribe", xmlData.getIsSubscribe());

        String sign = weixinPaymentHelper.buildPaySign(paramsMap, xmlData.getSignMethod());
        return xmlData.getAppSignature().equals(sign);
    }

}

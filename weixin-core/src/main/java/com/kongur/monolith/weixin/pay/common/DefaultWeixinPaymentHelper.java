package com.kongur.monolith.weixin.pay.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongur.monolith.weixin.core.common.utils.MD5Util;
import com.kongur.monolith.weixin.core.conf.WeixinConfigService;

/**
 * Ĭ�ϵ�΢��֧��������ʵ��
 * 
 * @author zhengwei
 */
@Service("weixinPaymentHelper")
public class DefaultWeixinPaymentHelper implements WeixinPaymentHelper {

    private final Logger        log = Logger.getLogger(getClass());

    @Autowired
    private WeixinConfigService weixinConfigService;

    /**
     * ����Ȼ������Ĳ�����װ��url��ʽ����(key=value)
     * 
     * @param paramMap
     * @param encodeValue �Ƿ��value����url encode
     * @return
     */
    public String buildUrlParamsStr(SortedMap<String, String> paramMap, String charset) {
        StringBuilder sb = new StringBuilder();
        Set<Entry<String, String>> es = paramMap.entrySet();
        for (Entry<String, String> entry : es) {
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (isNotBlank(charset)) {
                v = urlEncode(v, charset);
            }
            sb.append(k + "=" + v + "&");
        }

        String params = sb.substring(0, sb.lastIndexOf("&"));

        return params;
    }

    private boolean isNotBlank(String str) {
        return str != null && str != "";
    }

    // �����ַ�����
    private String urlEncode(String src, String charset) {
        String str = null;
        try {
            str = URLEncoder.encode(src, charset).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncoder error, charset=" + charset, e);
        }
        return str;

    }

    @Override
    public String buildNonceStr(String charset) {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), charset); // "UTF-8"
    }

    @Override
    public String buildPaySign(SortedMap<String, String> paramsMap) {
        return this.buildPaySign(paramsMap, EnumSignType.SHA1.getVal());
    }

    @Override
    public String buildPaySign(SortedMap<String, String> paramsMap, String signType) {
        if (log.isDebugEnabled()) {
            log.debug("buildPaySign, paySign params=" + paramsMap);
        }

        if (!EnumSignType.isSHA1(signType)) {
            throw new IllegalArgumentException("unsupport the signType, signType=" + signType + ", please use sha1.");
        }

        String params = this.buildUrlParamsStr(paramsMap, null);

        // ����֧��ǩ����Ҫ����URLENCODER��ԭʼֵ����SHA1�㷨��
        String sign = DigestUtils.sha1Hex(params);

        // System.out.println("paySign=" + sign);
        if (log.isDebugEnabled()) {
            log.debug("buildPaySign, paySign=" + sign);
        }

        return sign;
    }

    public WeixinConfigService getWeixinConfigService() {
        return weixinConfigService;
    }

    public void setWeixinConfigService(WeixinConfigService weixinConfigService) {
        this.weixinConfigService = weixinConfigService;
    }

}

package com.runssnail.monolith.weixin.client.trade;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.client.utils.MD5Util;

/**
 * 微信支付帮助类
 * 
 * @author zhengwei
 */
public abstract class PaymentHelper {

    private static final Logger log = Logger.getLogger(PaymentHelper.class);

    public static String buildPaySign(String appId, String prepayId, String paySignKey) {

        Assert.notNull(appId, "appId is required");
        Assert.notNull(prepayId, "prepayId is required");

        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appId", appId);
        params.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonceStr", buildNonce("UTF-8"));
        params.put("package", "prepay_id=" + prepayId);
        params.put("signType", EnumSignType.MD5.getVal());
        return buildPackageSign(params, paySignKey, EnumSignType.MD5);
    }

    public static String buildNonce(String charset) {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), charset); // "UTF-8"
    }

    /**
     * 生成sign
     * 
     * @param packageParams 参数
     * @param paySignKey 支付密钥
     * @return
     */
    public static String buildPackageSign(SortedMap<String, String> packageParams, String paySignKey) {

        return buildPackageSign(packageParams, paySignKey, EnumSignType.MD5);

    }

    /**
     * 生成签名sign
     * 
     * @param packageParams 参数
     * @param paySignKey 支付密钥
     * @param signType 签名方式
     * @return
     */
    public static String buildPackageSign(SortedMap<String, String> packageParams, String paySignKey,
                                          EnumSignType signType) {
        if (log.isDebugEnabled()) {
            log.debug("genPackageSign, packageParams=" + packageParams + ", signType=" + signType);
        }

        StringBuilder sb = new StringBuilder();

        String params = buildUrlParamsStr(packageParams, null);
        sb.append(params).append("&");
        sb.append("key=" + paySignKey);
        // System.out.println("genPackageSign params string=" + sb.toString());
        // String sign = MD5Util.MD5Encode(sb.toString(), charset)
        // .toUpperCase();
        String sign = null;
        if (signType.isMd5()) {
            sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        } else if (signType.isSHA1()) {
            sign = DigestUtils.sha1Hex(sb.toString()).toUpperCase();
        } else if (signType.isRsa()) {
            //
            throw new UnsupportedOperationException("unsupport current sign type, signType=" + signType);
        }

        if (log.isDebugEnabled()) {
            log.debug("genPackageSign, packageSign=" + sign);
        }

        return sign;
    }

    public static String buildUrlParamsStr(SortedMap<String, String> packageParams, String charset) {
        StringBuilder sb = new StringBuilder();
        Set<Entry<String, String>> es = packageParams.entrySet();
        for (Entry<String, String> entry : es) {
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (StringUtil.isNotBlank(charset)) {
                v = urlEncode(v, charset);
            }
            sb.append(k + "=" + v + "&");
        }

        String params = sb.substring(0, sb.lastIndexOf("&"));

        return params;
    }

    // 特殊字符处理
    private static String urlEncode(String src, String charset) {
        String str = null;
        try {
            str = URLEncoder.encode(src, charset).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncoder error, charset=" + charset, e);
        }
        return str;

    }
}

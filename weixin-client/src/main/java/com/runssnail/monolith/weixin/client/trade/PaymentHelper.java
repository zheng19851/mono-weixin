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

import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.client.utils.MD5Util;

/**
 * 微信支付帮助类
 * 
 * @author zhengwei
 */
public abstract class PaymentHelper {

    private static final Logger log = Logger.getLogger(PaymentHelper.class);

    /**
     * 生成js api 支付请求参数
     * 
     * @param appId
     * @param prepayId 微信预支付单id
     * @param paySignKey 微信支付密钥
     * @return 支付签名，随机字符串，时间戳
     */
    public static JsApiPayReq buildJsApiPayReq(String appId, String prepayId, String paySignKey) {
        JsApiPayReq req = new JsApiPayReq();
        String nonceStr = buildNonce("UTF-8");
        long timeStamp = System.currentTimeMillis();
        String paySgin = buildPaySign(appId, prepayId, paySignKey, nonceStr, timeStamp);

        req.setAppId(appId);
        req.setNonceStr(nonceStr);
        req.setTimeStamp(timeStamp);
        req.setPaySgin(paySgin);
        req.setPaySignKey(paySignKey);
        req.setPrepayId(prepayId);

        return req;
    }

    /**
     * 生成支付签名
     * 
     * @param appId 微信公众号id，必填
     * @param prepayId 微信预支付单id，必填
     * @param paySignKey 支付密钥，必填
     * @return
     */
    public static String buildPaySign(String appId, String prepayId, String paySignKey) {

        return buildPaySign(appId, prepayId, paySignKey, buildNonce("UTF-8"), System.currentTimeMillis());
    }

    /**
     * 生成支付签名
     * 
     * @param appId 微信公众号id，必填
     * @param prepayId 微信预支付单id，必填
     * @param paySignKey 支付密钥，必填
     * @param nonceStr 随机字符串，必填
     * @param timeStamp 时间戳，必填
     * @return
     */
    public static String buildPaySign(String appId, String prepayId, String paySignKey, String nonceStr, long timeStamp) {

        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appId", appId);
        params.put("timeStamp", String.valueOf(timeStamp));
        params.put("nonceStr", nonceStr);
        params.put("package", "prepay_id=" + prepayId);
        params.put("signType", EnumSignType.MD5.getVal());
        return buildPackageSign(params, paySignKey, EnumSignType.MD5);
    }

    /**
     * 生成随机字符串
     * 
     * @param charset
     * @return
     */
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

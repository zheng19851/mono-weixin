package com.kongur.monolith.weixin.pay.demo.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxap.util.MD5Util;

/**
 * 默认的微信支付帮助类实现
 * 
 * @author zhengwei
 */
@Service("weixinPaymentHelper")
public class DefaultWeixinPaymentHelper implements WeixinPaymentHelper {

    private final Logger        log = Logger.getLogger(getClass());

    @Autowired
    private WeixinConfigService weixinConfigService;

    /**
     * 生成package
     * 
     * @param packageParams 参数
     * @param charset 字符集
     * @return
     */
    private String buildPackage(SortedMap<String, String> packageParams, String charset) {
        String sign = buildPackageSign(packageParams);

        StringBuilder sb = new StringBuilder();

        String params = this.buildUrlParamsStr(packageParams, charset);
        sb.append(params).append("&");
        String packageValue = sb.append("sign=" + sign).toString();
        System.out.println("packageValue=" + packageValue);

        if (log.isDebugEnabled()) {
            log.debug("createPayRequestDTO, packageValue=" + packageValue);
        }

        return packageValue;
    }

    /**
     * 将自然排序过的参数组装成url形式参数(key=value)
     * 
     * @param paramMap
     * @param encodeValue 是否对value进行url encode
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

    // 特殊字符处理
    private String urlEncode(String src, String charset) {
        String str = null;
        try {
            str = URLEncoder.encode(src, charset).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncoder error, charset=" + charset, e);
        }
        return str;

    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public String buildPackageSign(SortedMap<String, String> packageParams) {
        return this.buildPackageSign(packageParams, EnumSignType.MD5);
    }
    
    @Override
    public String buildPackageSign(SortedMap<String, String> packageParams, EnumSignType signType) {
        if (log.isDebugEnabled()) {
            log.debug("genPackageSign, packageParams=" + packageParams + ", signType=" + signType);
        }

        StringBuffer sb = new StringBuffer();

        String params = buildUrlParamsStr(packageParams, null);
        sb.append(params).append("&");
        sb.append("key=" + weixinConfigService.getPaternerKey());
        // System.out.println("genPackageSign params string=" + sb.toString());
        // String sign = MD5Util.MD5Encode(sb.toString(), charset)
        // .toUpperCase();
        String sign = null;
        if(signType.isMd5()) {
            sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        } else if(signType.isSHA1()) {
            sign = DigestUtils.sha1Hex(sb.toString()).toUpperCase();
        } else if(signType.isRsa()) {
            //
            throw new UnsupportedOperationException("unsupport current sign type, signType=" + signType);
        }

        if (log.isDebugEnabled()) {
            log.debug("genPackageSign, packageSign=" + sign);
        }

        return sign;
    }

    @Override
    public String buildNonceStr(String charset) {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), charset); // "UTF-8"
    }

    @Override
    public String buildPackage(TradeDO trade) {

        SortedMap<String, String> packageParams = buildPackageParams(trade);

        return this.buildPackage(packageParams, trade.getInputCharset());
    }

    private SortedMap<String, String> buildPackageParams(TradeDO trade) {
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("bank_type", trade.getBankType()); // 支付类型
        packageParams.put("body", trade.getProductDesc()); // 商品描述
        packageParams.put("fee_type", trade.getFeeType()); // 银行币种
        packageParams.put("input_charset", trade.getInputCharset()); // 字符集
        packageParams.put("notify_url", weixinConfigService.getNotifyUrl()); // 通知地址
        packageParams.put("out_trade_no", trade.getTradeId()); // 商户订单号
        packageParams.put("partner", weixinConfigService.getPartnerId()); // 设置商户号
        packageParams.put("total_fee", String.valueOf(trade.getTotalFee())); // 商品总金额,以分为单位
        packageParams.put("spbill_create_ip", trade.getUserIp()); // 订单生成的机器IP，指用户浏览器端IP

        if (isNotBlank(trade.getAttach())) {
            packageParams.put("attach", trade.getAttach());
        }

        if (isNotBlank(trade.getTimeStart())) {
            packageParams.put("time_start", trade.getTimeStart());
        }

        if (isNotBlank(trade.getTimeExpire())) {
            packageParams.put("time_expire", trade.getTimeExpire());
        }

        if (trade.getTransportFee() != null) {
            packageParams.put("transport_fee", trade.getTransportFee().toString());
        }

        if (trade.getProductFee() != null) {
            packageParams.put("product_fee", trade.getProductFee().toString());
        }

        if (isNotBlank(trade.getGoodsTag())) {
            packageParams.put("goods_tag", trade.getGoodsTag());
        }

        return packageParams;
    }

    @Override
    public String buildPaySign(SortedMap<String, String> paramsMap) {
        return this.buildPaySign(paramsMap, "sha1");
    }

    @Override
    public String buildPaySign(SortedMap<String, String> paramsMap, String signType) {
        if (log.isDebugEnabled()) {
            log.debug("buildPaySign, paySign params=" + paramsMap);
        }

        if (!"sha1".equalsIgnoreCase(signType)) {
            throw new IllegalArgumentException("unsupport the signType, signType=" + signType + ", please use sha1.");
        }

        String params = this.buildUrlParamsStr(paramsMap, null);

        // 生成支付签名，要采用URLENCODER的原始值进行SHA1算法！
        String sign = DigestUtils.sha1Hex(params);

        System.out.println("paySign=" + sign);
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

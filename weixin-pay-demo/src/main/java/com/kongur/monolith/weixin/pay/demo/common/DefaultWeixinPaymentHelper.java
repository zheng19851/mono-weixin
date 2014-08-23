package com.kongur.monolith.weixin.pay.demo.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.wxap.util.MD5Util;

/**
 * 默认的微信支付帮助类实现
 * 
 * @author zhengwei
 */
@Service("weixinPaymentHelper")
public class DefaultWeixinPaymentHelper implements WeixinPaymentHelper {

    private final Logger log = Logger.getLogger(getClass());

    /**
     * 财付通商户权限密钥Key
     */
    private String       paternerKey;

    /**
     * 财付通商户身份的标识
     */
    private String       partnerId;

    /**
     * 接收微信通知的url
     */
    private String       notifyUrl;

    @PostConstruct
    public void init() {
        Assert.notNull(this.paternerKey, "请设置paternerKey");
        Assert.notNull(this.partnerId, "请设置partnerId");

        Assert.notNull(this.notifyUrl, "请设置notifyUrl");
    }

    /**
     * 生成package
     * 
     * @param packageParams 参数
     * @param charset 字符集
     * @return
     */
    private String buildPackage(SortedMap<String, String> packageParams, String charset) {
        String sign = genPackageSign(packageParams);

        StringBuilder sb = new StringBuilder();

        String params = this.buildUrlParamStr(packageParams, charset);
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
    public String buildUrlParamStr(SortedMap<String, String> paramMap, String charset) {
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

    private boolean isBlank(String str) {
        return !isNotBlank(str);
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
    private String genPackageSign(SortedMap<String, String> packageParams) {

        if (log.isDebugEnabled()) {
            log.debug("genPackageSign, packageParams=" + packageParams);
        }

        StringBuffer sb = new StringBuffer();

        String params = buildUrlParamStr(packageParams, null);
        sb.append(params).append("&");
        sb.append("key=" + this.paternerKey);
        // System.out.println("genPackageSign params string=" + sb.toString());
        // String sign = MD5Util.MD5Encode(sb.toString(), charset)
        // .toUpperCase();
        String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();

        if (log.isDebugEnabled()) {
            log.debug("genPackageSign, packageSign=" + sign);
        }

        return sign;

    }

    public String getPaternerKey() {
        return paternerKey;
    }

    public void setPaternerKey(String paternerKey) {
        this.paternerKey = paternerKey;
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
        packageParams.put("notify_url", this.notifyUrl); // 通知地址
        packageParams.put("out_trade_no", trade.getTradeId()); // 商户订单号
        packageParams.put("partner", this.partnerId); // 设置商户号
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
        if (log.isDebugEnabled()) {
            log.debug("buildPaySign, paySign params=" + paramsMap);
        }
        
        String params = this.buildUrlParamStr(paramsMap, null);

        // 生成支付签名，要采用URLENCODER的原始值进行SHA1算法！
        String sign = DigestUtils.sha1Hex(params);

        System.out.println("paySign=" + sign);
        if (log.isDebugEnabled()) {
            log.debug("buildPaySign, paySign=" + sign);
        }

        return sign;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

}

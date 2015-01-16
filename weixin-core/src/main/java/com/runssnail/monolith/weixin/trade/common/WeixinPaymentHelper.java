package com.runssnail.monolith.weixin.trade.common;

import java.util.SortedMap;

/**
 * 微信支付js api请求中和native api回调中的package参数生成服务 以及在创建请求时，所用到的一些公用方法
 * 
 * @author zhengwei
 */
public interface WeixinPaymentHelper {

    /**
     * 生成url参数形式的字符串(key=value&key2=value2)
     * 
     * @param paramMap 参数
     * @param charset 字符集，需要对value进行url encode，那么设置
     * @return
     */
    String buildUrlParamsStr(SortedMap<String, String> paramsMap, String charset);

    /**
     * 生成随机字符串
     * 
     * @param charset
     * @return
     */
    String buildNonceStr(String charset);

    /**
     * 生成付款签名，默认sha1签名方式
     * 
     * @param paramMap
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramMap);

    /**
     * 生成付款签名
     * 
     * @param paramsMap
     * @param signType
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramsMap, String signType);

    /**
     * 生成退款申请时的签名
     * 
     * @param paramsMap
     * @return
     */
    String buildRefundSign(SortedMap<String, String> paramsMap);

    /**
     * 创建签名sign
     * 
     * @param params
     * @return
     */
    String buildPackageSign(SortedMap<String, String> params, EnumSignType signType);
    
    /**
     * 创建签名sign，默认md5
     * 
     * @param params
     * @return
     */
    String buildPackageSign(SortedMap<String, String> params);
}

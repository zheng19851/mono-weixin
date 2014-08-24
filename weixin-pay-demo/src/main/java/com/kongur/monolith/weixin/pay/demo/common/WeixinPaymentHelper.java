package com.kongur.monolith.weixin.pay.demo.common;

import java.util.SortedMap;

/**
 * 微信支付js api请求中和native api回调中的package参数生成服务 以及在创建请求时，所用到的一些公用方法
 * 
 * @author zhengwei
 */
public interface WeixinPaymentHelper {

    /**
     * 生成package参数
     * 
     * @param trade package数据
     * @return
     */
    String buildPackage(TradeDO trade);

    /**
     * 生成package签名，默认md5加密
     * 
     * @param packageParams 参数
     * @return
     */
    String buildPackageSign(SortedMap<String, String> packageParams);

    /**
     * 生成package签名
     * 
     * @param packageParams 参数
     * @param signType 签名方式
     * @return
     */
    String buildPackageSign(SortedMap<String, String> packageParams, EnumSignType signType);

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
     * @param paramMap
     * @param signType 签名方式
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramMap, String signType);
}

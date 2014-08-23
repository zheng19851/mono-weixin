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
     * 生成url参数形式的字符串(key=value&key2=value2)
     * 
     * @param paramMap 参数
     * @param charset 字符集
     * @return
     */
    String buildUrlParamStr(SortedMap<String, String> paramMap, String charset);

    /**
     * 生成随机字符串
     * 
     * @param charset
     * @return
     */
    String buildNonceStr(String charset);
    
    /**
     * 生成付款签名
     * 
     * @param paramMap
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramMap);
}

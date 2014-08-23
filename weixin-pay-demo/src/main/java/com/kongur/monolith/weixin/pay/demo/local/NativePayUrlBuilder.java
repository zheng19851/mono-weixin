package com.kongur.monolith.weixin.pay.demo.local;

/**
 * 原生
 * 
 * @author zhengwei
 *
 */
public interface NativePayUrlBuilder {

    /**
     * 生成付款url
     * 
     * @param productId
     * @return
     */
    String buildPayUrl(String productId);
    
}

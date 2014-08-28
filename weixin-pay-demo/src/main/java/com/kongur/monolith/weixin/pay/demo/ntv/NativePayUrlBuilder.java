package com.kongur.monolith.weixin.pay.demo.ntv;

/**
 * 原生支付url生成器
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

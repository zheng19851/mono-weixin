package com.kongur.monolith.weixin.pay.demo.local;

/**
 * ԭ��
 * 
 * @author zhengwei
 *
 */
public interface NativePayUrlBuilder {

    /**
     * ���ɸ���url
     * 
     * @param productId
     * @return
     */
    String buildPayUrl(String productId);
    
}
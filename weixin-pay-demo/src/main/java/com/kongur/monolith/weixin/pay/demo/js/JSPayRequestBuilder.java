package com.kongur.monolith.weixin.pay.demo.js;

import com.kongur.monolith.weixin.pay.demo.common.TradeDO;



/**
 * 微信支付js api请求数据创建类
 * 
 * @author zhengwei
 *
 */
public interface JSPayRequestBuilder {

    /**
     * 创建微信支付数据
     * 
     * @param trade
     * @return
     */
    public JSPayRequestDTO buildPayRequest(TradeDO trade);
}

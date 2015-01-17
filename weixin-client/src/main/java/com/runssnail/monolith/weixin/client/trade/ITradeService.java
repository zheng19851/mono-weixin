package com.runssnail.monolith.weixin.client.trade;

import com.runssnail.monolith.common.result.Result;

/**
 * 微信交易服务
 * <p>
 * <li>1、先调用{@link #createPrepareOrder(String, String, TradeDTO)} 创建微信预支付订单，并记录prepayId
 * <li>2、用第1步返回的prepayId调用{@link #buildJsApiReq(String, String)} 生成jsapi支付用的paySign
 * @author zhengwei
 */
public interface ITradeService {

    /**
     * 创建微信预支付订单
     * 
     * @param appId 微信公众号id
     * @param trade 交易数据
     * @return 微信预支付单id
     */
    Result<PrepareOrderDTO> createPrepareOrder(String appId, TradeDTO trade);

   /**
    * 生成jsapi支付的请求参数
    * 
    * @param appId 微信公众号id
    * @param prepayId 微信预支付单id
    * @return
    */
    Result<JsApiPayReq> buildJsApiReq(String appId, String prepayId);

}

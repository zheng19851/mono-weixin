package com.runssnail.monolith.weixin.client.trade;

import com.runssnail.monolith.common.result.Result;

/**
 * 微信交易服务
 * 
 * @author zhengwei
 */
public interface ITradeService {

    /**
     * 创建预支付订单
     * 
     * @return 订单id
     */
    Result<PrepareOrderDTO> createPrepareOrder(String appId, TradeDTO trade);

    /**
     * 创建预支付订单
     * 
     * @return 订单id
     */
    // Result<String> createPrepareOrder(SortedMap<String, String> params);

}

package com.runssnail.monolith.weixin.client.trade;

import com.runssnail.monolith.common.result.Result;

/**
 * ΢�Ž��׷���
 * 
 * @author zhengwei
 */
public interface ITradeService {

    /**
     * ����Ԥ֧������
     * 
     * @return ����id
     */
    Result<PrepareOrderDTO> createPrepareOrder(String appId, TradeDTO trade);

    /**
     * ����Ԥ֧������
     * 
     * @return ����id
     */
    // Result<String> createPrepareOrder(SortedMap<String, String> params);

}

package com.runssnail.monolith.weixin.client.deliver;

import com.runssnail.monolith.common.result.Result;

/**
 * 发货通知，告知微信后台该订单的发货状态
 * <p>
 * 发货时间限制：虚拟、服务类24小时内，实物类72小时内。 请在收到支付通知后，按时发货，并使用发货通知接口将相关信息同步到微信后台。若 平台在规定时间内没有收到，将视作发货超时处理。
 * </p>
 * 
 * @author zhengwei
 */
public interface IDeliverNotifyService {

    /**
     * 发送通知
     * 
     * @param deliverInfo
     * @return
     */
    Result<Object> sendNotify(DeliverInfo deliverInfo);
}

package com.kongur.monolith.weixin.client.refund;

import java.util.List;

import com.kongur.monolith.common.result.Result;

/**
 * 退款服务
 * 
 * @author zhengwei
 */
public interface RemoteRefundService {

    /**
     * 申请退款
     * 
     * @param refundInfo
     * @return
     */
    Result<RefundInfo> refund(RefundApply refundApply);

    /**
     * 退款单查询
     * 
     * @param refundQuery
     * @return
     */
    Result<List<RefundInfo>> query(RefundQuery refundQuery);

}

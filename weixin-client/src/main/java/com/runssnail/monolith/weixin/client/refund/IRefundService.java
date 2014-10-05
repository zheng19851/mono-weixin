package com.runssnail.monolith.weixin.client.refund;

import java.util.List;

import com.runssnail.monolith.common.result.Result;

/**
 * �˿����
 * 
 * @author zhengwei
 */
public interface IRefundService {

    /**
     * �����˿�
     * 
     * @param refundInfo
     * @return
     */
    Result<RefundInfo> refund(RefundApply refundApply);

    /**
     * �˿��ѯ
     * 
     * @param refundQuery
     * @return
     */
    Result<List<RefundInfo>> query(RefundQuery refundQuery);

}

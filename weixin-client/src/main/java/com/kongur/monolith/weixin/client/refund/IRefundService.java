package com.kongur.monolith.weixin.client.refund;

import java.util.List;

import com.kongur.monolith.common.result.Result;

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

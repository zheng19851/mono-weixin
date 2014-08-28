package com.kongur.monolith.weixin.pay.refund;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.refund.IRefundService;
import com.kongur.monolith.weixin.client.refund.RefundApply;
import com.kongur.monolith.weixin.client.refund.RefundInfo;
import com.kongur.monolith.weixin.client.refund.RefundQuery;

/**
 * 默认退款服务实现
 * 
 * @author zhengwei
 */
@Service("refundService")
public class DefaultRefundService implements IRefundService {

    /**
     * 退款申请api
     */
    private String refundApiUrl            = "https://mch.tenpay.com/refundapi/gateway/refund.xml";

    /**
     * 查询退款明细api
     */
    private String queryRefundDetailApiUrl = "https://gw.tenpay.com/gateway/normalrefundquery.xml";

    @Override
    public Result<RefundInfo> refund(RefundApply refundInfo) {

        Result<RefundInfo> result = new Result<RefundInfo>();

        result.setSuccess(false);
        return result;
    }

    @Override
    public Result<List<RefundInfo>> query(RefundQuery refundQuery) {
        // TODO Auto-generated method stub
        return null;
    }

}

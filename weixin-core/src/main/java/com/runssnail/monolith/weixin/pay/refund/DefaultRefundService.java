package com.runssnail.monolith.weixin.pay.refund;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.refund.IRefundService;
import com.runssnail.monolith.weixin.client.refund.RefundApply;
import com.runssnail.monolith.weixin.client.refund.RefundInfo;
import com.runssnail.monolith.weixin.client.refund.RefundQuery;
import com.runssnail.monolith.weixin.core.base.service.ApiService;
import com.runssnail.monolith.weixin.pay.common.WeixinPaymentHelper;

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
    private String              refundApiUrl            = "https://mch.tenpay.com/refundapi/gateway/refund.xml";

    /**
     * 查询退款明细api
     */
    private String              queryRefundDetailApiUrl = "https://gw.tenpay.com/gateway/normalrefundquery.xml";

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    @Autowired
    private ApiService          apiService;

    @Override
    public Result<RefundInfo> refund(RefundApply refundInfo) {

        Result<RefundInfo> result = new Result<RefundInfo>();
        SortedMap<String, Object> paramsMap = new TreeMap<String, Object>();

        String sign = weixinPaymentHelper.buildRefundSign(paramsMap);

        result.setSuccess(false);
        return result;
    }

    @Override
    public Result<List<RefundInfo>> query(RefundQuery refundQuery) {
        // TODO Auto-generated method stub
        return null;
    }

}

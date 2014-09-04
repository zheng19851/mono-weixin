package com.kongur.monolith.weixin.pay.refund;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.refund.RefundApply;
import com.kongur.monolith.weixin.client.refund.RefundInfo;
import com.kongur.monolith.weixin.client.refund.RefundQuery;
import com.kongur.monolith.weixin.client.refund.RemoteRefundService;
import com.kongur.monolith.weixin.core.base.service.ApiService;
import com.kongur.monolith.weixin.pay.common.WeixinPaymentHelper;

/**
 * 默认退款服务实现
 * 
 * @author zhengwei
 */
@Service("refundService")
public class DefaultRefundService implements RemoteRefundService {

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

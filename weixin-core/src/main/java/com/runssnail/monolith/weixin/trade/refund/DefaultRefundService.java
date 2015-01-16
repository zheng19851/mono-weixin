package com.runssnail.monolith.weixin.trade.refund;

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
import com.runssnail.monolith.weixin.core.base.service.HttpClientService;

/**
 * Ĭ���˿����ʵ��
 * 
 * @author zhengwei
 */
@Service("refundService")
public class DefaultRefundService implements IRefundService {

    /**
     * �˿�����api
     */
    private String              refundApiUrl            = "https://mch.tenpay.com/refundapi/gateway/refund.xml";

    /**
     * ��ѯ�˿���ϸapi
     */
    private String              queryRefundDetailApiUrl = "https://gw.tenpay.com/gateway/normalrefundquery.xml";

//    @Autowired
//    private WeixinPaymentHelper weixinPaymentHelper;

    @Autowired
    private HttpClientService          apiService;

    @Override
    public Result<RefundInfo> refund(RefundApply refundInfo) {

        Result<RefundInfo> result = new Result<RefundInfo>();
        SortedMap<String, Object> paramsMap = new TreeMap<String, Object>();

        String sign = null; //TODO azhengwei ǩ�� weixinPaymentHelper.buildRefundSign(paramsMap);

        result.setSuccess(false);
        return result;
    }

    @Override
    public Result<List<RefundInfo>> query(RefundQuery refundQuery) {
        // TODO Auto-generated method stub
        return null;
    }

}

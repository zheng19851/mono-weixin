package com.runssnail.monolith.weixin.trade.deliver;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.deliver.DeliverInfo;
import com.runssnail.monolith.weixin.client.deliver.IDeliverNotifyService;
import com.runssnail.monolith.weixin.core.base.service.WeixinApiService;
import com.runssnail.monolith.weixin.trade.common.EnumSignType;
import com.runssnail.monolith.weixin.trade.common.WeixinPaymentHelper;

/**
 * @author zhengwei
 */
public class DefaultDeliverNotifyService implements IDeliverNotifyService {

    private String              deliverNotifyApiUrl   = "https://api.weixin.qq.com/pay/delivernotify?access_token=${access_token}";

    private String              deliverNotifyTemplate = "deliver/notify.vm";

    @Resource(name = "weixinApiService")
    private WeixinApiService    weixinApiService;

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    @Resource(name = "messageVelocityEngine")
    private VelocityEngine      velocityEngine;

    @Override
    public Result<Object> sendNotify(DeliverInfo deliverInfo) {

        Result<Object> result = new Result<Object>();

        SortedMap<String, Object> paramsMap = new TreeMap<String, Object>();
        paramsMap.put("appid", deliverInfo.getAppId());
        paramsMap.put("openid", deliverInfo.getOpenId());
        paramsMap.put("transid", deliverInfo.getTransId());
        paramsMap.put("out_trade_no", deliverInfo.getOutTradeNo());
        paramsMap.put("deliver_timestamp", String.valueOf(deliverInfo.getDeliverTimestamp()));
        paramsMap.put("deliver_status", String.valueOf(deliverInfo.getDeliverStatus()));
        paramsMap.put("deliver_msg", deliverInfo.getDeliverMsg());

        String sign = null;//TODO azhengwei Ç©Ãû weixinPaymentHelper.buildPaySign(paramsMap);
        paramsMap.put("app_signature", sign);
        paramsMap.put("sign_method", EnumSignType.SHA1.getVal());

        String postParams = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, deliverNotifyTemplate,
                                                                        paramsMap);
        Result<JSONObject> executeResult = weixinApiService.doPost(this.deliverNotifyApiUrl, postParams);
        if (!executeResult.isSuccess()) {
            result.setError(executeResult.getResultCode(), executeResult.getResultInfo());
            return result;
        }

        result.setSuccess(true);
        return result;
    }

}

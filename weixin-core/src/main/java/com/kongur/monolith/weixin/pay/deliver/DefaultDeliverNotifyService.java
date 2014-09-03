package com.kongur.monolith.weixin.pay.deliver;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.deliver.DeliverInfo;
import com.kongur.monolith.weixin.client.deliver.RemoteDeliverNotifyService;
import com.kongur.monolith.weixin.core.base.service.WeixinApiService;
import com.kongur.monolith.weixin.pay.common.EnumSignType;
import com.kongur.monolith.weixin.pay.common.WeixinPaymentHelper;

/**
 * @author zhengwei
 */
public class DefaultDeliverNotifyService implements RemoteDeliverNotifyService {

    private String              deliverNotifyApiUrl   = "https://api.weixin.qq.com/pay/delivernotify?access_token=${access_token}";

    private String              deliverNotifyTemplate = "deliver/notify.vm";

    @Autowired
    private WeixinApiService    weixinApiService;

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    @Resource(name = "messageVelocityEngine")
    private VelocityEngine      velocityEngine;

    @Override
    public Result<Object> sendNotify(DeliverInfo deliverInfo) {

        Result<Object> result = new Result<Object>();

        SortedMap<String, String> paramsMap = new TreeMap<String, String>();
        paramsMap.put("appid", deliverInfo.getAppId());
        paramsMap.put("openid", deliverInfo.getOpenId());
        paramsMap.put("transid", deliverInfo.getTransId());
        paramsMap.put("out_trade_no", deliverInfo.getOutTradeNo());
        paramsMap.put("deliver_timestamp", String.valueOf(deliverInfo.getDeliverTimestamp()));
        paramsMap.put("deliver_status", String.valueOf(deliverInfo.getDeliverStatus()));
        paramsMap.put("deliver_msg", deliverInfo.getDeliverMsg());

        String sign = weixinPaymentHelper.buildPaySign(paramsMap);
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

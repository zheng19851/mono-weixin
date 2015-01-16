package com.runssnail.monolith.weixin.trade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.lang.DateUtil;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.client.trade.ITradeService;
import com.runssnail.monolith.weixin.client.trade.PrepareOrderDTO;
import com.runssnail.monolith.weixin.client.trade.TradeDTO;
import com.runssnail.monolith.weixin.core.base.service.HttpClientService;
import com.runssnail.monolith.weixin.core.base.service.impl.DefaultHttpClientService;
import com.runssnail.monolith.weixin.core.common.utils.XmlTool;
import com.runssnail.monolith.weixin.core.conf.DefaultWeixinConfigService;
import com.runssnail.monolith.weixin.trade.common.DefaultWeixinPaymentHelper;
import com.runssnail.monolith.weixin.trade.common.WeixinPaymentHelper;

/**
 * Ĭ��΢�Ž���ʵ��
 * 
 * @author zhengwei
 */
@Service
public class DefaultTradeService implements ITradeService {

    private final Logger        log                      = Logger.getLogger(getClass());

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

//    @Resource(name = "messageVelocityEngine")
//    private VelocityEngine      velocityEngine;

    @Autowired
    private HttpClientService   httpClientService;

    private String              createPrepareOrderApiUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private String              defaultCharset           = "UTF-8";

    @Override
    public Result<PrepareOrderDTO> createPrepareOrder(String appId, TradeDTO trade) {

        if (log.isDebugEnabled()) {
            log.debug("createPrepareOrder, appId=" + appId + ", trade=" + trade);
        }

        Result<PrepareOrderDTO> result = new Result<PrepareOrderDTO>();

        // У�����

        SortedMap<String, String> params = getParams(appId, trade);

        // ����sign
        String sign = weixinPaymentHelper.buildPackageSign(params);
        params.put("sign", sign);

        // Map<String, Object> model = new HashMap<String, Object>(1);
        // model.put("trade", trade);
        // model.put("sign", sign);
        // ��װ�������
        // String prepareOrderParams = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
        // "trade/prepare_order.vm",
        // this.defaultCharset, model);
        String prepareOrderParams = XmlTool.toXml(params);

        Result<String> prepareOrderResult = httpClientService.executePost(createPrepareOrderApiUrl, prepareOrderParams);

        if (!prepareOrderResult.isSuccess()) {
            result.setError(prepareOrderResult.getResultCode(), prepareOrderResult.getResultInfo());
            return result;
        }

        Map<String, Object> xmlMap = XmlTool.toMap(prepareOrderResult.getResult());
        String returnCode = String.valueOf(xmlMap.get("return_code"));
        String resultCode = String.valueOf(xmlMap.get("result_code"));

        if (isSuccess(returnCode, resultCode)) {
            String prepayId = String.valueOf(xmlMap.get("prepay_id"));
            result.setSuccess(true);
            PrepareOrderDTO prepareOrder = new PrepareOrderDTO(prepayId);
            result.setResult(prepareOrder);
            String tradeType = String.valueOf(xmlMap.get("trade_type"));
            if ("NATIVE".equalsIgnoreCase(tradeType)) { // NATIVE����code_url
                prepareOrder.setCodeUrl(String.valueOf(xmlMap.get("code_url")));
            }

        } else if (!isReturnCodeSuccess(returnCode)) {
            result.setError(resultCode, String.valueOf(xmlMap.get("return_msg")));
        } else if (!isResultCodeSuccess(resultCode)) {
            result.setError(String.valueOf(xmlMap.get("err_code")), String.valueOf(xmlMap.get("err_code_des")));
        }

        return result;
    }

    private boolean isReturnCodeSuccess(String returnCode) {
        return "SUCCESS".equalsIgnoreCase(returnCode);
    }

    private boolean isSuccess(String returnCode, String resultCode) {
        return isReturnCodeSuccess(returnCode) && isResultCodeSuccess(resultCode);
    }

    private boolean isResultCodeSuccess(String resultCode) {
        return "SUCCESS".equalsIgnoreCase(resultCode);
    }

    private SortedMap<String, String> getParams(String appId, TradeDTO trade) {

        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", appId);
        params.put("mch_id", trade.getMchId()); // �̻���

        if (StringUtil.isNotBlank(trade.getDeviceInfo())) {
            params.put("device_info", trade.getDeviceInfo()); // �豸��
        }
        params.put("nonce_str", this.weixinPaymentHelper.buildNonceStr(defaultCharset));
        params.put("body", trade.getProductDesc()); // ��Ʒ����
        if (StringUtil.isNotBlank(trade.getAttach())) {
            params.put("attach", trade.getAttach()); // �������ݣ�ԭ������
        }
        params.put("out_trade_no", trade.getOrderId()); // �̻�ϵͳ�ڲ��Ķ�����
        params.put("total_fee", String.valueOf(trade.getTotalFee())); // ��λ��
        params.put("spbill_create_ip", trade.getIp()); // ip
        if (trade.getCreateTime() != null) {
            params.put("time_start", DateUtil.getDateTime("yyyyMMddHHmmss", trade.getCreateTime())); // ��������ʱ�䣬��ʽΪyyyyMMddHHmmss
        }

        if (trade.getExpireTime() != null) {
            params.put("time_expire", DateUtil.getDateTime("yyyyMMddHHmmss", trade.getExpireTime())); // ����ʧЧʱ�䣬��ʽΪyyyyMMddHHmmss
        }

        if (StringUtil.isNotBlank(trade.getGoodsTag())) {
            params.put("goods_tag", trade.getGoodsTag()); // ��Ʒ���
        }

        params.put("notify_url", trade.getNotifyUrl());
        params.put("trade_type", trade.getTradeType()); // JSAPI��NATIVE��APP
        params.put("openid", trade.getOpenId()); // �û����̻�appid�µ�Ψһ��ʶ��trade_typeΪJSAPIʱ���˲����ش�

        if (StringUtil.isNotBlank(trade.getProductId())) {
            params.put("product_id", trade.getProductId()); // ��ƷID, ֻ��trade_typeΪNATIVEʱ��Ҫ��д
        }

        return params;
    }

    public static void main(String[] args) throws Exception {

        // paraeXml();

        DefaultTradeService tradeService = new DefaultTradeService();

        DefaultWeixinConfigService configService = new DefaultWeixinConfigService();

        configService.setPaySignkey("hfhaf97fj32kj32jk98f98a833fajfa9"); // ֧����Կ
        configService.setPaternerKey("0c6f80694c277a64a50bc29870e9d058");
        // configService.setPartnerId("1226202301");
        // configService.setNotifyUrl("http://weixin.qq.com");
        configService.setAppId("wxc829b42548f53840");

        DefaultWeixinPaymentHelper helper = new DefaultWeixinPaymentHelper();
        helper.setWeixinConfigService(configService);
        tradeService.weixinPaymentHelper = helper;

        DefaultHttpClientService httpClientService = new DefaultHttpClientService();
        tradeService.httpClientService = httpClientService;

        TradeDTO trade = new TradeDTO();
        trade.setMchId("10065789");
        trade.setProductDesc("����");
        trade.setOrderId("21719919122");
        trade.setTotalFee(1L);
        trade.setIp("127.0.0.1");
        trade.setNotifyUrl("http://weixin.qq.com");
        trade.setTradeType("JSAPI");
        trade.setOpenId("ovcPajq3X8K03aW-PBwtvfweuV44");
        Result<PrepareOrderDTO> result = tradeService.createPrepareOrder(configService.getAppId(), trade);
        System.out.println(result);
    }

    private static void paraeXml() throws Exception {
        FileInputStream is = new FileInputStream(new File("D:/docs/che/prepare_result.xml"));

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuilder sb = new StringBuilder();
        String v = null;
        while ((v = br.readLine()) != null) {
            sb.append(v);
        }

        br.close();

        Map<String, Object> xmlMap = XmlTool.toMap(sb.toString());

        if ("SUCCESS".equals(xmlMap.get("return_code"))) {
            System.out.println(xmlMap.get("prepay_id"));
        }
        System.out.println(xmlMap);

    }
}
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
import org.springframework.util.Assert;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.lang.DateUtil;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.client.trade.EnumSignType;
import com.runssnail.monolith.weixin.client.trade.EnumTradeType;
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
 * 默认微信交易实现
 * 
 * @author zhengwei
 */
@Service
public class DefaultTradeService implements ITradeService {

    private final Logger        log                      = Logger.getLogger(getClass());

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;

    @Autowired
    private HttpClientService   httpClientService;

    private String              createPrepareOrderApiUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private String              defaultCharset           = "UTF-8";

    @Override
    public Result<PrepareOrderDTO> createPrepareOrder(String appId, String merchantId, TradeDTO trade) {
        if (log.isDebugEnabled()) {
            log.debug("createPrepareOrder, appId=" + appId + ", trade=" + trade);
        }

        // 校验参数
        Assert.notNull(appId, "appId is required");
        Assert.notNull(merchantId, "merchantId is required");
        Assert.notNull(trade, "trade is required");

        Assert.notNull(trade.getProductDesc(), "productDesc is required");

        Assert.notNull(trade.getOrderId(), "orderId is required");
        Assert.notNull(trade.getTotalFee(), "totalFee is required");

        Assert.notNull(trade.getIp(), "ip is required");
        Assert.notNull(trade.getNotifyUrl(), "notifyUrl is required");
        Assert.notNull(trade.getTradeType(), "tradeType is required");

        if (trade.getTradeType().isJsApi()) {
            Assert.notNull(trade.getOpenId(), "openId is required");
        }

        Result<PrepareOrderDTO> result = new Result<PrepareOrderDTO>();

        SortedMap<String, String> params = getParams(appId, merchantId, trade);

        // 创建sign
        String sign = weixinPaymentHelper.buildPackageSign(params);
        params.put("sign", sign);

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
            if ("NATIVE".equalsIgnoreCase(tradeType)) { // NATIVE才有code_url
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

    private SortedMap<String, String> getParams(String appId, String merchantId, TradeDTO trade) {

        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", appId);
        params.put("mch_id", merchantId); // 商户号

        if (StringUtil.isNotBlank(trade.getDeviceInfo())) {
            params.put("device_info", trade.getDeviceInfo()); // 设备号
        }
        params.put("nonce_str", this.weixinPaymentHelper.buildNonceStr(defaultCharset));
        params.put("body", trade.getProductDesc()); // 商品描述
        if (StringUtil.isNotBlank(trade.getAttach())) {
            params.put("attach", trade.getAttach()); // 附加数据，原样返回
        }
        params.put("out_trade_no", trade.getOrderId()); // 商户系统内部的订单号
        params.put("total_fee", String.valueOf(trade.getTotalFee())); // 单位分
        params.put("spbill_create_ip", trade.getIp()); // ip
        if (trade.getCreateTime() != null) {
            params.put("time_start", DateUtil.getDateTime("yyyyMMddHHmmss", trade.getCreateTime())); // 订单生成时间，格式为yyyyMMddHHmmss
        }

        if (trade.getExpireTime() != null) {
            params.put("time_expire", DateUtil.getDateTime("yyyyMMddHHmmss", trade.getExpireTime())); // 订单失效时间，格式为yyyyMMddHHmmss
        }

        if (StringUtil.isNotBlank(trade.getGoodsTag())) {
            params.put("goods_tag", trade.getGoodsTag()); // 商品标记
        }

        params.put("notify_url", trade.getNotifyUrl());
        params.put("trade_type", trade.getTradeType().getVal()); // JSAPI、NATIVE、APP
        params.put("openid", trade.getOpenId()); // 用户在商户appid下的唯一标识，trade_type为JSAPI时，此参数必传

        if (StringUtil.isNotBlank(trade.getProductId())) {
            params.put("product_id", trade.getProductId()); // 商品ID, 只在trade_type为NATIVE时需要填写
        }

        return params;
    }

    @Override
    public String buildPaySign(String appId, String prepayId) {

        Assert.notNull(appId, "appId is required");
        Assert.notNull(prepayId, "prepayId is required");

        SortedMap<String, String> params = new TreeMap<String, String>();
        params.put("appId", appId);
        params.put("timeStamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonceStr", weixinPaymentHelper.buildNonceStr(this.defaultCharset));
        params.put("package", "prepay_id=" + prepayId);
        params.put("signType", EnumSignType.MD5.getVal());
        return this.weixinPaymentHelper.buildPackageSign(params, EnumSignType.MD5);
    }

    public static void main(String[] args) throws Exception {

        // createPrepare();

        buildPaySign();

    }

    private static void buildPaySign() {
        DefaultTradeService tradeService = new DefaultTradeService();

        DefaultWeixinConfigService configService = new DefaultWeixinConfigService();

        configService.setPaySignkey("hfhaf97fj32kj32jk98f98a833fajfa9"); // 支付密钥
        configService.setPaternerKey("0c6f80694c277a64a50bc29870e9d058");
        // configService.setPartnerId("1226202301");
        // configService.setNotifyUrl("http://weixin.qq.com");
        configService.setAppId("wxc829b42548f53840");

        DefaultWeixinPaymentHelper helper = new DefaultWeixinPaymentHelper();
        helper.setWeixinConfigService(configService);
        tradeService.weixinPaymentHelper = helper;

        DefaultHttpClientService httpClientService = new DefaultHttpClientService();
        tradeService.httpClientService = httpClientService;
        // =======================

        String paySign = tradeService.buildPaySign("wxc829b42548f53840", "wx20150116220707c04e031fa20204223168");
        System.out.println(paySign);

    }

    private static void createPrepare() {
        DefaultTradeService tradeService = new DefaultTradeService();

        DefaultWeixinConfigService configService = new DefaultWeixinConfigService();

        configService.setPaySignkey("hfhaf97fj32kj32jk98f98a833fajfa9"); // 支付密钥
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
        // trade.setMchId("10065789");
        trade.setProductDesc("外套");
        trade.setOrderId("21719919122");
        trade.setTotalFee(1L);
        trade.setIp("127.0.0.1");
        trade.setNotifyUrl("http://weixin.qq.com");
        trade.setTradeType(EnumTradeType.JSAPI);
        trade.setOpenId("ovcPajq3X8K03aW-PBwtvfweuV44");
        Result<PrepareOrderDTO> result = tradeService.createPrepareOrder(configService.getAppId(), "10065789", trade);
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

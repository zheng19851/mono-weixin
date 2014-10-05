package com.runssnail.monolith.weixin.pay.demo.ntv;

/**
 * Native（原生）支付回调商户后台post数据验证
 * 
 * @author zhengwei
 */
public interface NativePayCallbackXmlDataValidator {

    /**
     * 验证
     * 
     * @param xmlData
     * @return
     */
    boolean validate(NativePayCallbackXmlDataDO xmlData);

}

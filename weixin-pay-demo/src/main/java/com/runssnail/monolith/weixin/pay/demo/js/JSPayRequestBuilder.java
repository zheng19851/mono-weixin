package com.runssnail.monolith.weixin.pay.demo.js;

import com.runssnail.monolith.weixin.pay.demo.common.TradeDO;



/**
 * ΢��֧��js api�������ݴ�����
 * 
 * @author zhengwei
 *
 */
public interface JSPayRequestBuilder {

    /**
     * ����΢��֧������
     * 
     * @param trade
     * @return
     */
    public JSPayRequestDTO buildPayRequest(TradeDO trade);
}

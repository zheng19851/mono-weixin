package com.runssnail.monolith.weixin.core.test.service.trade;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.trade.ITradeService;
import com.runssnail.monolith.weixin.client.trade.PrepareOrderDTO;
import com.runssnail.monolith.weixin.client.trade.TradeDTO;
import com.runssnail.monolith.weixin.core.test.TestBase;


public class TradeServiceTest extends TestBase {

    @Autowired
    private ITradeService tradeService;
    
    @Test 
    public void testCreate() {
        TradeDTO trade = new TradeDTO();
        trade.setMchId("10065789");
        trade.setProductDesc("ÍâÌ×");
        trade.setOrderId("21719919122");
        trade.setTotalFee(1L);
        trade.setIp("127.0.0.1");
        trade.setNotifyUrl("http://weixin.qq.com");
        trade.setTradeType("JSAPI");
        trade.setOpenId("ovcPajq3X8K03aW-PBwtvfweuV44");
        Result<PrepareOrderDTO> result = tradeService.createPrepareOrder("wxc829b42548f53840", trade);
        System.out.println(result);
    }
}

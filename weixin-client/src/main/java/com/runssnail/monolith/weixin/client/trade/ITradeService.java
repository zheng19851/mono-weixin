package com.runssnail.monolith.weixin.client.trade;

import com.runssnail.monolith.common.result.Result;

/**
 * ΢�Ž��׷���
 * <p>
 * <li>1���ȵ���{@link #createPrepareOrder(String, String, TradeDTO)} ����΢��Ԥ֧������������¼prepayId
 * <li>2���õ�1�����ص�prepayId����{@link #buildJsApiReq(String, String)} ����jsapi֧���õ�paySign
 * @author zhengwei
 */
public interface ITradeService {

    /**
     * ����΢��Ԥ֧������
     * 
     * @param appId ΢�Ź��ں�id
     * @param trade ��������
     * @return ΢��Ԥ֧����id
     */
    Result<PrepareOrderDTO> createPrepareOrder(String appId, TradeDTO trade);

   /**
    * ����jsapi֧�����������
    * 
    * @param appId ΢�Ź��ں�id
    * @param prepayId ΢��Ԥ֧����id
    * @return
    */
    Result<JsApiPayReq> buildJsApiReq(String appId, String prepayId);

}

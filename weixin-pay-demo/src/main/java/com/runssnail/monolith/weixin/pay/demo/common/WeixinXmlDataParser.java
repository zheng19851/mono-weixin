package com.runssnail.monolith.weixin.pay.demo.common;

import com.runssnail.monolith.common.result.Result;

/**
 * ΢��xml���ݽ�����
 * 
 * @author zhengwei
 */
public interface WeixinXmlDataParser {

    /**
     * ��xml���ݽ����ɶ���
     * 
     * @param xmlData xml����
     * @param clazz �����class����
     * @return
     */
    <T> Result<T> parse(String xmlData, Class<T> clazz);

}

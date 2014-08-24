package com.kongur.monolith.weixin.pay.demo.common;

import com.kongur.monolith.common.result.Result;

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

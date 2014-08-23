package com.kongur.monolith.weixin.pay.demo.common;

import java.util.SortedMap;

/**
 * ΢��֧��js api�����к�native api�ص��е�package�������ɷ��� �Լ��ڴ�������ʱ�����õ���һЩ���÷���
 * 
 * @author zhengwei
 */
public interface WeixinPaymentHelper {

    /**
     * ����package����
     * 
     * @param trade package����
     * @return
     */
    String buildPackage(TradeDO trade);

    /**
     * ����url������ʽ���ַ���(key=value&key2=value2)
     * 
     * @param paramMap ����
     * @param charset �ַ���
     * @return
     */
    String buildUrlParamStr(SortedMap<String, String> paramMap, String charset);

    /**
     * ��������ַ���
     * 
     * @param charset
     * @return
     */
    String buildNonceStr(String charset);
    
    /**
     * ���ɸ���ǩ��
     * 
     * @param paramMap
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramMap);
}

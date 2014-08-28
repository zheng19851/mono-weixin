package com.kongur.monolith.weixin.pay.common;

import java.util.SortedMap;

/**
 * ΢��֧��js api�����к�native api�ص��е�package�������ɷ��� �Լ��ڴ�������ʱ�����õ���һЩ���÷���
 * 
 * @author zhengwei
 */
public interface WeixinPaymentHelper {

    /**
     * ����url������ʽ���ַ���(key=value&key2=value2)
     * 
     * @param paramMap ����
     * @param charset �ַ�������Ҫ��value����url encode����ô����
     * @return
     */
    String buildUrlParamsStr(SortedMap<String, String> paramsMap, String charset);

    /**
     * ��������ַ���
     * 
     * @param charset
     * @return
     */
    String buildNonceStr(String charset);

    /**
     * ���ɸ���ǩ����Ĭ��sha1ǩ����ʽ
     * 
     * @param paramMap
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramsMap);

    /**
     * ���ɸ���ǩ��
     * 
     * @param paramMap
     * @param signType ǩ����ʽ
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramsMap, String signType);
}

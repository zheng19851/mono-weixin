package com.runssnail.monolith.weixin.trade.common;

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
    String buildPaySign(SortedMap<String, String> paramMap);

    /**
     * ���ɸ���ǩ��
     * 
     * @param paramsMap
     * @param signType
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramsMap, String signType);

    /**
     * �����˿�����ʱ��ǩ��
     * 
     * @param paramsMap
     * @return
     */
    String buildRefundSign(SortedMap<String, String> paramsMap);

    /**
     * ����ǩ��sign
     * 
     * @param params
     * @return
     */
    String buildPackageSign(SortedMap<String, String> params, EnumSignType signType);
    
    /**
     * ����ǩ��sign��Ĭ��md5
     * 
     * @param params
     * @return
     */
    String buildPackageSign(SortedMap<String, String> params);
}
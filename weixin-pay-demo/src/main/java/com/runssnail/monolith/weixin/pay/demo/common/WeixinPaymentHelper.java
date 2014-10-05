package com.runssnail.monolith.weixin.pay.demo.common;

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
     * ����packageǩ����Ĭ��md5����
     * 
     * @param packageParams ����
     * @return
     */
    String buildPackageSign(SortedMap<String, String> packageParams);

    /**
     * ����packageǩ��
     * 
     * @param packageParams ����
     * @param signType ǩ����ʽ
     * @return
     */
    String buildPackageSign(SortedMap<String, String> packageParams, EnumSignType signType);

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
     * @param paramMap
     * @param signType ǩ����ʽ
     * @return
     */
    String buildPaySign(SortedMap<String, String> paramMap, String signType);
}

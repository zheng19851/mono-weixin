package com.kongur.monolith.weixin.pay.demo.common;

/**
 * ΢�����÷���
 * 
 * @author zhengwei
 */
public interface WeixinConfigService {

    /**
     * �Ƹ�ͨ�̻�Ȩ����ԿKey
     * 
     * @return
     */
    String getPaternerKey();

    /**
     * ����΢��֪ͨ��url
     * 
     * @return
     */
    String getNotifyUrl();

    /**
     * �Ƹ�ͨid
     * 
     * @return
     */
    String getPartnerId();

    /**
     * ΢��Ĭ���ַ���
     * 
     * @return
     */
    String getCharset();

    /**
     * ΢�Ź��ں�appid
     * 
     * @return
     */
    String getAppId();

    /**
     * ΢��֧����Կ
     * 
     * @return
     */
    String getPaySignkey();
}

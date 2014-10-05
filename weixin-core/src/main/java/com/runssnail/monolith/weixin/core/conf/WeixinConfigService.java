package com.runssnail.monolith.weixin.core.conf;

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
     * ΢�Ź��ں�appid
     * 
     * @return
     */
    String getAppId();

    /**
     * ΢�Ź��ں�appSecret
     * 
     * @return
     */
    String getAppSecret();

    /**
     * token
     * 
     * @return
     */
    String getToken();

    /**
     * �жϴ����appid�Ƿ�Ĭ�ϵ�appid
     * 
     * @param appId
     * @return
     */
    boolean isDefaultAppId(String appId);
}

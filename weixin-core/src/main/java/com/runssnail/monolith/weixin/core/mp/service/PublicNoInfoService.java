package com.runssnail.monolith.weixin.core.mp.service;

import java.util.List;

import com.runssnail.monolith.weixin.core.mp.domain.PublicNoInfoDO;

/**
 * ͳһ�����ں���Ϣ����
 * 
 * @author zhengwei
 */
public interface PublicNoInfoService {

    /**
     * ����appid��ȡ���ں���Ϣ
     * 
     * @param appId
     * @return
     */
    PublicNoInfoDO getPublicNoInfoByAppId(String appId);

    /**
     * token
     * 
     * @param appId
     * @return
     */
    String getTokenByAppId(String appId);

    /**
     * AppSecret
     * 
     * @param appId
     * @return
     */
    String getAppSecretByAppId(String appId);

    /**
     * ���ں���Ϣ
     * 
     * @return
     */
    List<PublicNoInfoDO> getPublicNoInfoList();

    /**
     * appid�Ƿ����
     * 
     * @param appId
     * @return
     */
    boolean exists(String appId);

    /**
     * ��ȡĬ�ϵ�appid
     * 
     * @return
     */
    String getDefaultAppId();

    /**
     * Ĭ�ϵ�PaternerKey
     * 
     * @return
     */
    String getDefaultPaternerKey();

    /**
     * Ĭ�ϵ�token
     * 
     * @return
     */
    String getDefaultToken();

    /**
     * �Ƿ�Ĭ�ϵ�appid
     * 
     * @param appId
     * @return
     */
    boolean isDefaultAppId(String appId);

}

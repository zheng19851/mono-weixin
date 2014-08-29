package com.kongur.monolith.weixin.core.mp.service;

import java.util.List;

import com.kongur.monolith.weixin.core.mp.domain.PublicNoInfoDO;

/**
 * 统一管理公众号信息服务
 * 
 * @author zhengwei
 */
public interface PublicNoInfoService {

    /**
     * 根据appid获取公众号信息
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
     * 公众号信息
     * 
     * @return
     */
    List<PublicNoInfoDO> getPublicNoInfoList();

    /**
     * appid是否存在
     * 
     * @param appId
     * @return
     */
    boolean exists(String appId);

    /**
     * 获取默认的appid
     * 
     * @return
     */
    String getDefaultAppId();

    /**
     * 默认的PaternerKey
     * 
     * @return
     */
    String getDefaultPaternerKey();

    /**
     * 默认的token
     * 
     * @return
     */
    String getDefaultToken();

    /**
     * 是否默认的appid
     * 
     * @param appId
     * @return
     */
    boolean isDefaultAppId(String appId);

}

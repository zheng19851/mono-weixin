package com.kongur.monolith.weixin.core.base.service;

import com.kongur.monolith.common.result.Result;

/**
 * AccessToken管理服务
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface AccessTokenService {

    /**
     * 刷新公众号access token
     * 
     * @param appId 公众号appid
     * @return
     */
    Result<String> refresh(String appId);

    /**
     * 刷新 access token
     * 
     * @return
     */
    Result<String> refresh();

    /**
     * 获取默认公众号的AccessToken
     * 
     * @return
     */
    String getAccessToken();

    /**
     * 根据微信appid获取对应的accessToken
     * 
     * @param appId
     * @return
     */
    String getAccessToken(String appId);

}

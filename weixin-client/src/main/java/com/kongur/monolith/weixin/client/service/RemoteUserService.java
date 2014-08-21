package com.kongur.monolith.weixin.client.service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.domain.dto.User;

/**
 * 获取微信用户信息服务
 * 
 * @author zhengwei
 */
public interface RemoteUserService {

    /**
     * OAuth2.0 获取用户基本信息
     * 
     * @param code OAuth2.0授权时的CODE
     * @return
     */
    Result<User> getUserByOAuth2Code(String code);

    /**
     * 网页授权获取用户id
     * 
     * @param code OAuth2.0授权时的CODE
     * @return
     */
    Result<String> getOpenIdByOAuth2Code(String code);

}

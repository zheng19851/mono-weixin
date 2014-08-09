package com.kongur.monolith.weixin.common.service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.User;

/**
 * 获取微信用户信息服务
 * 
 * @author zhengwei
 */
public interface RemoteUserService {

    /**
     * OAuth2.0 获取用户基本信息
     * 
     * @param openId 微信用户id
     * @param accessToken OAuth2.0中的accessToken
     * @return
     */
    Result<User> getOAuth2UserById(String openId, String accessToken);

    /**
     * 网页授权获取用户id
     * 
     * @param code OAuth2.0授权时的CODE
     * @return
     */
    Result<String> getOpenIdByCode(String code);

}

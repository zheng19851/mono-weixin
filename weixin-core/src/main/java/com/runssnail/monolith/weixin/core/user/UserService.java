package com.runssnail.monolith.weixin.core.user;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.user.IUserService;
import com.runssnail.monolith.weixin.client.user.User;


/**
 * 用户信息服务
 * 
 * @author zhengwei
 *
 */
public interface UserService extends IUserService {

    /**
     * OAuth2.0 获取用户基本信息，使用默认的appId
     * 
     * @param code OAuth2.0授权时的CODE
     * @see getUserByOAuth2Code(String appId, String code)
     * @return
     */
    Result<User> getUserByOAuth2Code(String code);

    /**
     * 网页授权获取用户id，使用默认的appId
     * 
     * @param code OAuth2.0授权时的CODE
     * @see getOpenIdByOAuth2Code(String appId, String code)
     * @return
     */
    Result<String> getOpenIdByOAuth2Code(String code);

}

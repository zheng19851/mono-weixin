package com.runssnail.monolith.weixin.core.user;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.user.IUserService;
import com.runssnail.monolith.weixin.client.user.User;


/**
 * �û���Ϣ����
 * 
 * @author zhengwei
 *
 */
public interface UserService extends IUserService {

    /**
     * OAuth2.0 ��ȡ�û�������Ϣ��ʹ��Ĭ�ϵ�appId
     * 
     * @param code OAuth2.0��Ȩʱ��CODE
     * @see getUserByOAuth2Code(String appId, String code)
     * @return
     */
    Result<User> getUserByOAuth2Code(String code);

    /**
     * ��ҳ��Ȩ��ȡ�û�id��ʹ��Ĭ�ϵ�appId
     * 
     * @param code OAuth2.0��Ȩʱ��CODE
     * @see getOpenIdByOAuth2Code(String appId, String code)
     * @return
     */
    Result<String> getOpenIdByOAuth2Code(String code);

}
package com.kongur.monolith.weixin.client.user;

import com.kongur.monolith.common.result.Result;

/**
 * ��ȡ΢���û���Ϣ����
 * 
 * @author zhengwei
 */
public interface RemoteUserService {

    /**
     * OAuth2.0 ��ȡ�û�������Ϣ
     * 
     * @param code OAuth2.0��Ȩʱ��CODE
     * @return
     */
    Result<User> getUserByOAuth2Code(String code);

    /**
     * ��ҳ��Ȩ��ȡ�û�id
     * 
     * @param code OAuth2.0��Ȩʱ��CODE
     * @return
     */
    Result<String> getOpenIdByOAuth2Code(String code);

}

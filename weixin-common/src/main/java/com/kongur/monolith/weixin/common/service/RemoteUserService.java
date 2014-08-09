package com.kongur.monolith.weixin.common.service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.User;

/**
 * ��ȡ΢���û���Ϣ����
 * 
 * @author zhengwei
 */
public interface RemoteUserService {

    /**
     * OAuth2.0 ��ȡ�û�������Ϣ
     * 
     * @param openId ΢���û�id
     * @param accessToken OAuth2.0�е�accessToken
     * @return
     */
    Result<User> getOAuth2UserById(String openId, String accessToken);

    /**
     * ��ҳ��Ȩ��ȡ�û�id
     * 
     * @param code OAuth2.0��Ȩʱ��CODE
     * @return
     */
    Result<String> getOpenIdByCode(String code);

}

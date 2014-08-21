package com.kongur.monolith.weixin.core.base.service;

import com.kongur.monolith.common.result.Result;

/**
 * AccessToken�������
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface AccessTokenService {

    /**
     * ˢ�� access token
     * 
     * @return
     */
    Result<String> refresh();

    /**
     * ��ȡAccessToken
     * 
     * @return
     */
    String getAccessToken();

}

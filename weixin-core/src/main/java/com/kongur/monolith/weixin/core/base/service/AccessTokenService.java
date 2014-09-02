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
     * ˢ�¹��ں�access token
     * 
     * @param appId ���ں�appid
     * @return
     */
    Result<String> refresh(String appId);

    /**
     * ˢ�� access token
     * 
     * @return
     */
    Result<String> refresh();

    /**
     * ��ȡĬ�Ϲ��ںŵ�AccessToken
     * 
     * @return
     */
    String getAccessToken();

    /**
     * ����΢��appid��ȡ��Ӧ��accessToken
     * 
     * @param appId
     * @return
     */
    String getAccessToken(String appId);

}

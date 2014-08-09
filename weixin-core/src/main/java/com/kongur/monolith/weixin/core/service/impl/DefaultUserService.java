package com.kongur.monolith.weixin.core.service.impl;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.common.domain.dto.User;
import com.kongur.monolith.weixin.common.service.RemoteUserService;
import com.kongur.monolith.weixin.core.service.WeixinApiService;

/**
 * @author zhengwei
 */
@Service("userService")
public class DefaultUserService implements RemoteUserService {

    private final Logger     log = Logger.getLogger(getClass());

    @Autowired
    private WeixinApiService weixinApiService;

    /**
     * ��ȡ�û�oauth2.0��ȨʱAccessToken��url
     */
    @Value("weixin.get_oauth2_access_token.url")
    private String           getOauth2AccessTokenUrl;

    @PostConstruct
    public void init() {
        if (StringUtil.isBlank(this.getOauth2AccessTokenUrl)) {
            throw new IllegalArgumentException("the getOauth2AccessTokenUrl can not be blank!");
        }

        if (log.isDebugEnabled()) {
            log.debug("the getOauth2AccessTokenUrl ->" + this.getOauth2AccessTokenUrl);
        }

    }

    @Override
    public Result<User> getOAuth2UserById(String openId, String accessToken) {
        throw new UnsupportedOperationException("��ʱ��֧��");
    }

    @Override
    public Result<String> getOpenIdByCode(String code) {
        Result<String> result = new Result<String>();

        if (StringUtil.isBlank(code)) {
            result.setError("10001", "code ����Ϊ��.");
            return result;
        }

        // �滻code����
        String apiUrl = this.getOauth2AccessTokenUrl;
        apiUrl = MessageFormat.format(apiUrl, code);

        Result<JSONObject> apiResult = weixinApiService.doGet(apiUrl, false);

        if (!apiResult.isSuccess()) {
            result.setError(apiResult.getResultCode(), apiResult.getResultInfo());
            return result;
        }

        String openId = apiResult.getResult().getString("openid");
        result.setResult(openId);

        result.setSuccess(true);
        return result;
    }

}

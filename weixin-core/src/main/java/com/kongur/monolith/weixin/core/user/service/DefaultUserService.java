package com.kongur.monolith.weixin.core.user.service;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.client.user.RemoteUserService;
import com.kongur.monolith.weixin.client.user.User;
import com.kongur.monolith.weixin.core.base.service.WeixinApiService;
import com.kongur.monolith.weixin.core.mp.service.PublicNoInfoService;

/**
 * ΢���û�����
 * 
 * @author zhengwei
 */
@Service("userService")
public class DefaultUserService implements RemoteUserService {

    private final Logger        log                  = Logger.getLogger(getClass());

    @Autowired
    private WeixinApiService    weixinApiService;

//    @Autowired
//    private WeixinConfigService weixinConfigService;

    @Autowired
    private PublicNoInfoService publicNoInfoService;

    /**
     * ��ȡ�û�oauth2.0��ȨʱAccessToken��url
     */
    @Value("${weixin.get_oauth2_access_token.url}")
    private String              getOauth2AccessTokenUrl;

    /**
     * �����ҳ��Ȩ������Ϊsnsapi_userinfo�����ʱ�����߿���ͨ��access_token��openid��ȡ�û���Ϣ
     */
    @Value("${weixin.get_oauth2_user_info.url}")
    private String              getOauth2UserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";

    @PostConstruct
    public void init() {
        if (StringUtil.isBlank(this.getOauth2AccessTokenUrl)) {
            throw new IllegalArgumentException("the getOauth2AccessTokenUrl can not be blank!");
        }

        if (StringUtil.isBlank(this.getOauth2UserInfoUrl)) {
            throw new IllegalArgumentException("the getOauth2UserInfoUrl can not be blank!");
        }

        if (log.isDebugEnabled()) {
            log.debug("the getOauth2AccessTokenUrl ->" + this.getOauth2AccessTokenUrl);
        }

        if (log.isDebugEnabled()) {
            log.debug("the getOauth2UserInfoUrl ->" + this.getOauth2UserInfoUrl);
        }

    }

    @Override
    public Result<User> getUserByOAuth2Code(String code) {

        if (log.isDebugEnabled()) {
            log.debug("invoke getOAuth2UserByCode method, code=" + code);
        }

        return this.getUserByOAuth2Code(publicNoInfoService.getDefaultAppId(), code);
    }

    private User buildUser(String openId, JSONObject userJsonObj) {
        User user = new User();
        // openid �û���Ψһ��ʶ
        user.setOpenid(openId);
        // nickname �û��ǳ�
        user.setNickName(userJsonObj.getString("nickname"));
        // sex �û����Ա�ֵΪ1ʱ�����ԣ�ֵΪ2ʱ��Ů�ԣ�ֵΪ0ʱ��δ֪
        user.setSex(userJsonObj.getString("sex"));
        // province �û�����������д��ʡ��
        user.setProvince(userJsonObj.getString("province"));
        // city ��ͨ�û�����������д�ĳ���
        user.setCity(userJsonObj.getString("city"));
        // country ���ң����й�ΪCN
        user.setCountry(userJsonObj.getString("country"));
        // headimgurl �û�ͷ�����һ����ֵ����������ͷ���С����0��46��64��96��132��ֵ��ѡ��0����640*640������ͷ�񣩣��û�û��ͷ��ʱ����Ϊ��
        user.setHeadimgurl(userJsonObj.getString("headimgurl"));
        // privilege
        user.setPrivilege(userJsonObj.getString("privilege"));
        return user;

    }

    @Override
    public Result<String> getOpenIdByOAuth2Code(String code) {

        return this.getOpenIdByOAuth2Code(publicNoInfoService.getDefaultAppId(), code);
    }

    @Override
    public Result<User> getUserByOAuth2Code(String appId, String code) {
        if (log.isDebugEnabled()) {
            log.debug("invoke getOAuth2UserByCode method, appId=" + appId + ", code=" + code);
        }

        Result<User> result = new Result<User>();

        if (StringUtil.isBlank(appId)) {
            result.setError("10002", "appid ����Ϊ��.");
            return result;
        }
        if (StringUtil.isBlank(code)) {
            result.setError("10001", "code ����Ϊ��.");
            return result;
        }

        if (!publicNoInfoService.exists(appId)) {
            result.setError("10003", "appId ������.");
            return result;
        }

        String getAccessTokenApiUrl = getOAuth20AccessTokenApiUrl(appId, code);

        Result<JSONObject> getAccessTokenResult = weixinApiService.doGet(getAccessTokenApiUrl, false);

        if (!getAccessTokenResult.isSuccess()) {
            result.setError(getAccessTokenResult.getResultCode(), getAccessTokenResult.getResultInfo());
            return result;
        }

        String openId = getAccessTokenResult.getResult().getString("openid");
        String accessToken = getAccessTokenResult.getResult().getString("access_token");

        String getUserInfoApiUrl = this.getOauth2UserInfoUrl;

        getUserInfoApiUrl = MessageFormat.format(getUserInfoApiUrl, accessToken, openId);

        Result<JSONObject> getUserInfoResult = weixinApiService.doGet(getUserInfoApiUrl, false);

        if (!getUserInfoResult.isSuccess()) {
            result.setError(getUserInfoResult.getResultCode(), getUserInfoResult.getResultInfo());
            return result;
        }

        JSONObject userJsonObj = getUserInfoResult.getResult();

        User user = buildUser(openId, userJsonObj);
        result.setResult(user);
        result.setSuccess(true);
        return result;
    }

    private String getOAuth20AccessTokenApiUrl(String appId, String code) {
        // �滻code����
        String getAccessTokenApiUrl = this.getOauth2AccessTokenUrl;

        String appSecret = publicNoInfoService.getAppSecretByAppId(appId);

        getAccessTokenApiUrl = MessageFormat.format(getAccessTokenApiUrl, appId, appSecret, code);

        return getAccessTokenApiUrl;

    }

    @Override
    public Result<String> getOpenIdByOAuth2Code(String appId, String code) {
        if (log.isDebugEnabled()) {
            log.debug("invoke getOpenIdByCode method, appId=" + appId + " code=" + code);
        }

        Result<String> result = new Result<String>();
        if (StringUtil.isBlank(appId)) {
            result.setError("10002", "appid ����Ϊ��.");
            return result;
        }

        if (StringUtil.isBlank(code)) {
            result.setError("10001", "code ����Ϊ��.");
            return result;
        }

        String getAccessTokenApiUrl = getOAuth20AccessTokenApiUrl(appId, code);

        Result<JSONObject> apiResult = weixinApiService.doGet(getAccessTokenApiUrl, false);

        if (!apiResult.isSuccess()) {
            result.setError(apiResult.getResultCode(), apiResult.getResultInfo());
            return result;
        }

        String openId = apiResult.getResult().getString("openid");
        result.setResult(openId);

        if (log.isDebugEnabled()) {
            log.debug("invoke getOpenIdByCode method successfully, openId=" + openId);
        }
        result.setSuccess(true);
        return result;
    }

}

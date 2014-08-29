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
 * 微信用户服务
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
     * 获取用户oauth2.0授权时AccessToken的url
     */
    @Value("${weixin.get_oauth2_access_token.url}")
    private String              getOauth2AccessTokenUrl;

    /**
     * 如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息
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
        // openid 用户的唯一标识
        user.setOpenid(openId);
        // nickname 用户昵称
        user.setNickName(userJsonObj.getString("nickname"));
        // sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
        user.setSex(userJsonObj.getString("sex"));
        // province 用户个人资料填写的省份
        user.setProvince(userJsonObj.getString("province"));
        // city 普通用户个人资料填写的城市
        user.setCity(userJsonObj.getString("city"));
        // country 国家，如中国为CN
        user.setCountry(userJsonObj.getString("country"));
        // headimgurl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
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
            result.setError("10002", "appid 不能为空.");
            return result;
        }
        if (StringUtil.isBlank(code)) {
            result.setError("10001", "code 不能为空.");
            return result;
        }

        if (!publicNoInfoService.exists(appId)) {
            result.setError("10003", "appId 不存在.");
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
        // 替换code参数
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
            result.setError("10002", "appid 不能为空.");
            return result;
        }

        if (StringUtil.isBlank(code)) {
            result.setError("10001", "code 不能为空.");
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

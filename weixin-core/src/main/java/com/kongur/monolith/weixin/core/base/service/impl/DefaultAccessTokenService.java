package com.kongur.monolith.weixin.core.base.service.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.base.service.AccessTokenService;
import com.kongur.monolith.weixin.core.base.service.WeixinApiService;
import com.kongur.monolith.weixin.core.mp.domain.PublicNoInfoDO;
import com.kongur.monolith.weixin.core.mp.service.PublicNoInfoService;
import com.kongur.monolith.weixin.core.support.WeixinApiHelper;

/**
 * 微信 AccessToken 服务
 * <p>
 * 微信的AccessToken会定时实效，默认是7200秒(2个小时)，因此需要定时去刷新AccessToken
 * </p>
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Service("defaultAccessTokenService")
public class DefaultAccessTokenService implements AccessTokenService {

    private final Logger             log              = Logger.getLogger(getClass());

    /**
     * 主动调用微信平台接口时需要用到
     */
    private volatile String          accessToken;

    // @Autowired
    // private WeixinConfigService weixinConfigService;

    /**
     * 默认的appid
     */
    @Value("${weixin.appId}")
    private String                   appId;

    /**
     * 默认微信账号的的appSecret
     */
    @Value("${weixin.appSecret}")
    private String                   appSecret;

    @Value("${weixin.api.token.url}")
    private String                   apiTokenUrl;

    @Resource(name = "defaultWeixinApiService")
    private WeixinApiService         apiService;

    /**
     * 定时刷新accessToken用
     */
    private ScheduledExecutorService executor;

    /**
     * 刷新时段，默认没5400秒(一个半小时)刷新一次
     */
    @Value("${weixin.api.token.refresh.period}")
    private int                      refreshPeriod    = 5400;

    @Value("${weixin.api.token.refresh.disable}")
    private boolean                  disableRefresh   = false;

    /**
     * key=appid
     */
    private Map<String, String>      accessTokenCache = new ConcurrentHashMap<String, String>();

    @Autowired
    private PublicNoInfoService      publicNoInfoService;

    @PostConstruct
    public void init() {

        Assert.notNull(this.appId, "the appId can not be blank.");
        Assert.notNull(this.appSecret, "the appSecret can not be blank.");
        Assert.notNull(this.apiTokenUrl, "the apiTokenUrl can not be blank.");

        // this.apiTokenUrl = MessageFormat.format(this.apiTokenUrlPattern, this.appId, this.appSecret);

        if (log.isInfoEnabled()) {
            log.info("the appId->" + this.appId);
            log.info("the appSecret->" + this.appSecret);
            log.info("the api for access token url is->" + this.apiTokenUrl);
        }

        if (!disableRefresh) {
            if (executor == null) {
                executor = Executors.newSingleThreadScheduledExecutor();
            }

            executor.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    try {
                        refresh();
                    } catch (Exception e) {
                        log.error("refresh access token error, apiTokenUrl=" + apiTokenUrl, e);
                    }
                }

            }, 5, this.refreshPeriod, TimeUnit.SECONDS);
        }

    }

    /**
     * 刷新access token
     * 
     * @param appId
     * @param appSecret
     * @return
     */
    private Result<JSONObject> refreshOne(String appId, String appSecret) {

        String oldAccessToken = this.getAccessToken(appId);

        String apiTokenUrl = this.apiTokenUrl;

        // 替换appId和appSecret
        apiTokenUrl = MessageFormat.format(apiTokenUrl, appId, appSecret);

        Result<JSONObject> result = apiService.doGet(apiTokenUrl, false);

        if (!result.isSuccess()) {
            log.error("refresh access token error, apiTokenUrl=" + apiTokenUrl + ", errorCode="
                      + result.getResultCode() + ", errorInfo=" + result.getResultInfo());

            return result;
        }

        final JSONObject jsonObj = result.getResult();

        if (WeixinApiHelper.containsAccessToken(jsonObj)) {
            String newAccessToken = WeixinApiHelper.getAccessToken(jsonObj);
            if (this.publicNoInfoService.isDefaultAppId(appId)) {
                this.accessToken = newAccessToken;
            }

            // 更新accessToken
            this.accessTokenCache.put(appId, newAccessToken);

            if (log.isInfoEnabled()) {
                log.info("refresh access token successfully, appId=" + appId + ", appSecret=" + appSecret
                         + ".\n oldAccessToken=" + oldAccessToken + "\n newAccessToken=" + newAccessToken);
            }

            return result;
        }

        log.error("refresh access token error, response=" + result.getResult());
        result.setError("2001", "can not find access token.");
        return result;

    }

    /**
     * 刷新AccessToken
     */
    public Result<String> refresh() {

        if (log.isInfoEnabled()) {
            log.info("refresh access token start");
        }

        final Result<String> result = new Result<String>();

        // 刷新默认的公众号accessToken
        // if (StringUtil.isNotBlank(this.appId)) {
        // try {
        // refreshOne(this.appId, this.appSecret);
        // } catch (Exception e) {
        // log.error("refresh default accessToken error, appId=" + appId + ", appSecret=" + appSecret, e);
        // }
        // }

        List<PublicNoInfoDO> publicNoInfoList = publicNoInfoService.getPublicNoInfoList();

        if (publicNoInfoList != null && !publicNoInfoList.isEmpty()) {
            if (log.isInfoEnabled()) {
                log.info("there are " + publicNoInfoList.size() + " publicNos to be refresh access token.");
            }
            for (PublicNoInfoDO info : publicNoInfoList) {
                try {
                    refreshOne(info.getAppId(), info.getAppSecret());
                } catch (Exception e) {
                    log.error("refresh accessToken error, PublicNoInfoDO=" + info, e);
                }
            }
        } else {
            log.warn("there are without any publicNos to be refresh access token.");
        }

        if (log.isInfoEnabled()) {
            log.info("refresh access token end.");
        }

        result.setSuccess(true);
        return result;

    }

    @Override
    public String getAccessToken() {

        String token = this.accessToken;
        if (StringUtil.isBlank(token)) {
            Result<String> result = refresh();
            if (result.isSuccess()) {
                token = result.getResult();
            }
        }

        return token;
    }

    @PreDestroy
    public void destroy() {
        if (this.executor != null) {
            this.executor.shutdown();
        }
    }

    @Override
    public String getAccessToken(String appId) {
        if (this.appId.equals(appId)) {
            return this.accessToken;
        }
        return this.accessTokenCache.get(appId);
    }

    public static void main(String[] args) {
        AtomicReference<String> ref = new AtomicReference<String>();

        System.out.println(ref.compareAndSet(null, "new"));
    }

}

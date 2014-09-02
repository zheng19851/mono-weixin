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
 * ΢�� AccessToken ����
 * <p>
 * ΢�ŵ�AccessToken�ᶨʱʵЧ��Ĭ����7200��(2��Сʱ)�������Ҫ��ʱȥˢ��AccessToken
 * </p>
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Service("defaultAccessTokenService")
public class DefaultAccessTokenService implements AccessTokenService {

    private final Logger             log              = Logger.getLogger(getClass());

    /**
     * ��������΢��ƽ̨�ӿ�ʱ��Ҫ�õ�
     */
    // private volatile String accessToken;

    // @Autowired
    // private WeixinConfigService weixinConfigService;

    /**
     * Ĭ�ϵ�appid
     */
    // @Value("${weixin.appId}")
    // private String appId;
    //
    // /**
    // * Ĭ��΢���˺ŵĵ�appSecret
    // */
    // @Value("${weixin.appSecret}")
    // private String appSecret;

    @Value("${weixin.api.token.url}")
    private String                   apiTokenUrl;

    @Resource(name = "defaultWeixinApiService")
    private WeixinApiService         apiService;

    /**
     * ��ʱˢ��accessToken��
     */
    private ScheduledExecutorService executor;

    /**
     * ˢ��ʱ�Σ�Ĭ��û5400��(һ����Сʱ)ˢ��һ��
     */
    @Value("${weixin.api.token.refresh.period}")
    private int                      refreshPeriod    = 5400;

    @Value("${weixin.api.token.refresh.disable}")
    private boolean                  disableRefresh   = false;

    /**
     * access token���棬key=appid
     */
    private Map<String, String>      accessTokenCache = new ConcurrentHashMap<String, String>();

    @Autowired
    private PublicNoInfoService      publicNoInfoService;

    @PostConstruct
    public void init() {

        // Assert.notNull(this.appId, "the appId can not be blank.");
        // Assert.notNull(this.appSecret, "the appSecret can not be blank.");
        Assert.notNull(this.apiTokenUrl, "the apiTokenUrl can not be blank.");

        // this.apiTokenUrl = MessageFormat.format(this.apiTokenUrlPattern, this.appId, this.appSecret);

        if (log.isInfoEnabled()) {
            // log.info("the appId->" + this.appId);
            // log.info("the appSecret->" + this.appSecret);
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
     * ˢ��access token
     * 
     * @param appId
     * @param appSecret
     * @return
     */
    private Result<String> refreshOne(String appId, String appSecret) {

        Result<String> result = new Result<String>();

        if (StringUtil.isBlank(appId)) {
            result.setError("10001", "the appId can not empty");
            return result;
        }

        if (StringUtil.isBlank(appSecret)) {
            result.setError("10002", "the appSecret can not empty");
            return result;
        }

        String oldAccessToken = this.getAccessToken(appId);

        String apiTokenUrl = this.apiTokenUrl;

        // �滻appId��appSecret
        apiTokenUrl = MessageFormat.format(apiTokenUrl, appId, appSecret);

        Result<JSONObject> apiResult = apiService.doGet(apiTokenUrl, false);

        if (!apiResult.isSuccess()) {
            log.error("refresh access token error, apiTokenUrl=" + apiTokenUrl + ", errorCode="
                      + apiResult.getResultCode() + ", errorInfo=" + apiResult.getResultInfo());

            result.setError(apiResult.getResultCode(), apiResult.getResultInfo());
            return result;
        }

        final JSONObject jsonObj = apiResult.getResult();

        if (WeixinApiHelper.containsAccessToken(jsonObj)) {
            String newAccessToken = WeixinApiHelper.getAccessToken(jsonObj);
            // if (this.publicNoInfoService.isDefaultAppId(appId)) {
            // this.accessToken = newAccessToken;
            // }

            // ����accessToken
            this.accessTokenCache.put(appId, newAccessToken);

            if (log.isInfoEnabled()) {
                log.info("refresh access token successfully, appId=" + appId + ", appSecret=" + appSecret
                         + ", oldAccessToken=" + oldAccessToken + ", newAccessToken=" + newAccessToken);
            }

            result.setResult(newAccessToken);
            return result;
        }

        log.error("refresh access token error, response=" + apiResult.getResult());
        result.setError("2001", "can not find access token.");
        return result;

    }

    @Override
    public Result<String> refresh(String appId) {
        String appSecret = this.publicNoInfoService.getAppSecretByAppId(appId);
        return this.refreshOne(appId, appSecret);
    }

    /**
     * ˢ��AccessToken
     */
    public Result<String> refresh() {

        if (log.isInfoEnabled()) {
            log.info("refresh access token start");
        }

        final Result<String> result = new Result<String>();

        // ˢ��Ĭ�ϵĹ��ں�accessToken
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

        // String token = this.accessToken;
        // if (StringUtil.isBlank(token)) {
        // Result<String> result = refresh();
        // if (result.isSuccess()) {
        // token = result.getResult();
        // }
        // }

        return this.getAccessToken(publicNoInfoService.getDefaultAppId());
    }

    @PreDestroy
    public void destroy() {
        if (this.executor != null) {
            this.executor.shutdown();
        }
    }

    @Override
    public String getAccessToken(String appId) {

        return this.accessTokenCache.get(appId);
    }

    public static void main(String[] args) {
        AtomicReference<String> ref = new AtomicReference<String>();

        System.out.println(ref.compareAndSet(null, "new"));
    }

}

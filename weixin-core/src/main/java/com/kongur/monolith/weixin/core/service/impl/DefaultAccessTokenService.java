package com.kongur.monolith.weixin.core.service.impl;

import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.service.AccessTokenService;
import com.kongur.monolith.weixin.core.service.WeixinApiService;
import com.kongur.monolith.weixin.core.utils.WeixinApiHelper;

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

    private final Logger             log                = Logger.getLogger(getClass());

    /**
     * ��������΢��ƽ̨�ӿ�ʱ��Ҫ�õ�
     */
    private volatile String          accessToken;

    @Value("${weixin.appId}")
    private String                   appId;

    @Value("${weixin.appSecret}")
    private String                   appSecret;

    @Value("${weixin.api.token.url.pattern}")
    private String                   apiTokenUrlPattern = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

    private String                   apiTokenUrl;

    @Resource(name = "defaultWeixinApiService")
    private WeixinApiService               apiService;

    private ScheduledExecutorService executor;

    /**
     * ˢ��ʱ�Σ�Ĭ��û5400��(һ����Сʱ)ˢ��һ��
     */
    @Value("${weixin.api.token.refresh.period}")
    private int                      refreshPeriod      = 5400;

    @Value("${weixin.api.token.refresh.disable}")
    private boolean                  disableRefresh     = false;

    @PostConstruct
    public void init() {

        Assert.notNull(this.appId, "the appId can not be blank.");
        Assert.notNull(this.appSecret, "the appSecret can not be blank.");
        Assert.notNull(this.apiTokenUrlPattern, "the apiTokenPattern can not be blank.");

        this.apiTokenUrl = MessageFormat.format(this.apiTokenUrlPattern, this.appId, this.appSecret);

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
     * ˢ��AccessToken
     */
    public Result<String> refresh() {

        final Result<String> result = new Result<String>();

        Result<JSONObject> jsonResult = apiService.doGet(apiTokenUrl, false);

        if (!jsonResult.isSuccess()) {
            log.error("refresh access token error, apiTokenUrl=" + apiTokenUrl + ", errorCode="
                      + jsonResult.getResultCode() + ", errorInfo=" + jsonResult.getResultInfo());

            result.setError(jsonResult.getResultCode(), jsonResult.getResultInfo());
            return result;
        }

        final JSONObject jsonObj = jsonResult.getResult();

        if (WeixinApiHelper.containsAccessToken(jsonObj)) {
            String newAccessToken = WeixinApiHelper.getAccessToken(jsonObj);
            this.accessToken = newAccessToken;
            result.setResult(newAccessToken);

            if (log.isDebugEnabled()) {
                log.debug("refresh access token successfully, new AccessToken=" + newAccessToken);
            }

        } else {
            log.error("refresh access token error, response=" + jsonResult.getResult());
            result.setError("2001", "can not find access token.");
            return result;
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

    public static void main(String[] args) {
        AtomicReference<String> ref = new AtomicReference<String>();

        System.out.println(ref.compareAndSet(null, "new"));
    }
}

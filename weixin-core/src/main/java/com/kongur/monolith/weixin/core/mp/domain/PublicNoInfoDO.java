package com.kongur.monolith.weixin.core.mp.domain;

import com.kongur.monolith.common.DomainBase;

/**
 * 公众号数据
 * 
 * @author zhengwei
 */
public class PublicNoInfoDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -1894755335160121712L;

    private String            appId;

    private String            appSecret;

    private String            token;

    /**
     * 微信支付里的paternerKey
     */
    private String            paternerKey;

    private Integer           status           = 1;

    public PublicNoInfoDO() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPaternerKey() {
        return paternerKey;
    }

    public void setPaternerKey(String paternerKey) {
        this.paternerKey = paternerKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

package com.runssnail.monolith.weixin.core.mp.domain;

import java.util.Date;

import com.runssnail.monolith.common.DomainBase;

/**
 * ���ں�����
 * 
 * @author zhengwei
 */
public class PublicNoInfoDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -1894755335160121712L;

    private String            id;

    private String            name;

    private String            appId;

    private String            appSecret;

    private String            token;

    /**
     * ΢��֧�����paternerKey
     */
    private String            paternerKey;

    private Integer           status           = 1;

    private Date              gmtCreate;

    public PublicNoInfoDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
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

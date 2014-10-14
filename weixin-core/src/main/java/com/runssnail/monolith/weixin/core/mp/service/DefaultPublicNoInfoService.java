package com.runssnail.monolith.weixin.core.mp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.weixin.core.conf.WeixinConfigService;
import com.runssnail.monolith.weixin.core.mp.domain.PublicNoInfoDO;
import com.runssnail.monolith.weixin.core.mp.manager.PublicNoInfoManager;

/**
 * @author zhengwei
 */
@Service("publicNoInfoService")
public class DefaultPublicNoInfoService implements PublicNoInfoService {

    @Autowired
    private PublicNoInfoManager publicNoInfoManager;

    @Autowired
    private WeixinConfigService weixinConfigService;

    /**
     * 默认的公众号信息
     */
    private PublicNoInfoDO      defaultPublicNoInfoCache;

    @PostConstruct
    public void init() {

        // 设置默认的公众号(配置文件server.properties里的公众号)
        PublicNoInfoDO defaultPublicNo = new PublicNoInfoDO();
        defaultPublicNo.setAppId(weixinConfigService.getAppId());
        defaultPublicNo.setAppSecret(weixinConfigService.getAppSecret());
        defaultPublicNo.setToken(weixinConfigService.getToken());
        defaultPublicNo.setPaternerKey(weixinConfigService.getPaternerKey());

        this.defaultPublicNoInfoCache = defaultPublicNo;
    }

    @Override
    public PublicNoInfoDO getPublicNoInfoByAppId(String appId) {
        if (weixinConfigService.isDefaultAppId(appId)) {
            return this.defaultPublicNoInfoCache;
        }

        return this.publicNoInfoManager.getPublicNoInfoByAppId(appId);
    }

    @Override
    public String getTokenByAppId(String appId) {

        if (weixinConfigService.isDefaultAppId(appId)) {
            return weixinConfigService.getToken();
        }

        return this.publicNoInfoManager.getTokenByAppId(appId);
    }

    @Override
    public String getAppSecretByAppId(String appId) {
        if (weixinConfigService.isDefaultAppId(appId)) {
            return weixinConfigService.getAppSecret();
        }

        return this.publicNoInfoManager.getAppSecretByAppId(appId);
    }

    @Override
    public List<PublicNoInfoDO> getPublicNoInfoList() {
        List<PublicNoInfoDO> retList = new ArrayList<PublicNoInfoDO>();
        retList.add(defaultPublicNoInfoCache);
        List<PublicNoInfoDO> otherPublicNoList = this.publicNoInfoManager.getPublicNoInfoList();
        if (otherPublicNoList != null && !otherPublicNoList.isEmpty()) {
            for (PublicNoInfoDO info : otherPublicNoList) {
                if (!isDefaultAppId(info.getAppId())) { // 过滤掉默认的公众号
                    retList.add(info);
                }
            }
        }
        return retList;
    }

    @Override
    public boolean exists(String appId) {

        if (weixinConfigService.isDefaultAppId(appId)) {
            return true;
        }

        return this.publicNoInfoManager.exists(appId);
    }

    @Override
    public String getDefaultAppId() {
        return this.defaultPublicNoInfoCache.getAppId();
    }

    @Override
    public String getDefaultPaternerKey() {
        return this.defaultPublicNoInfoCache.getPaternerKey();
    }

    @Override
    public String getDefaultToken() {
        return this.defaultPublicNoInfoCache.getToken();
    }

    @Override
    public boolean isDefaultAppId(String appId) {
        return this.defaultPublicNoInfoCache.getAppId().equals(appId);
    }

    @Override
    public boolean hasEnabled(String appId) {
        if(isDefaultAppId(appId)) {
            return this.defaultPublicNoInfoCache.isEnabled();
        }
        
        return publicNoInfoManager.isEnabled(appId);
    }

}

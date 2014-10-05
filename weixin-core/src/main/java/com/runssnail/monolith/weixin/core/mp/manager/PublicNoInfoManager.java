package com.runssnail.monolith.weixin.core.mp.manager;

import java.util.List;

import com.runssnail.monolith.weixin.core.mp.domain.PublicNoInfoDO;

/**
 * 公众号管理
 * 
 * @author zhengwei
 */
public interface PublicNoInfoManager {
    
    void refresh();

    /**
     * 根据appid获取公众号信息
     * 
     * @param appId
     * @return
     */
    PublicNoInfoDO getPublicNoInfoByAppId(String appId);

    String getTokenByAppId(String appId);

    String getAppSecretByAppId(String appId);

    /**
     * 公众号信息
     * 
     * @return
     */
    List<PublicNoInfoDO> getPublicNoInfoList();

    /**
     * appid是否存在
     * 
     * @param appId
     * @return
     */
    boolean exists(String appId);
    
    
}

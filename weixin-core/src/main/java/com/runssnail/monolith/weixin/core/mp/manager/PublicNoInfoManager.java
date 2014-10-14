package com.runssnail.monolith.weixin.core.mp.manager;

import java.util.List;

import com.runssnail.monolith.weixin.core.mp.domain.PublicNoInfoDO;

/**
 * ���ںŹ���
 * 
 * @author zhengwei
 */
public interface PublicNoInfoManager {
    
    void refresh();

    /**
     * ����appid��ȡ���ں���Ϣ
     * 
     * @param appId
     * @return
     */
    PublicNoInfoDO getPublicNoInfoByAppId(String appId);

    String getTokenByAppId(String appId);

    String getAppSecretByAppId(String appId);

    /**
     * ���ں���Ϣ
     * 
     * @return
     */
    List<PublicNoInfoDO> getPublicNoInfoList();

    /**
     * appid�Ƿ����
     * 
     * @param appId
     * @return
     */
    boolean exists(String appId);

    boolean isEnabled(String appId);
    
    
}

package com.kongur.monolith.weixin.client.menu;

import java.util.List;

import com.kongur.monolith.common.result.Result;

/**
 * 菜单服务，给内部系统调用
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public interface RemoteMenuService {

    void refresh(String appId);

    /**
     * 创建菜单
     * 
     * @param appId 微信appId
     * @param menus
     * @return
     */
    Result<Object> createMenus(String appId, List<Menu> menus);

    /**
     * 删除所有菜单
     * 
     * @param appId 微信appId
     * @return
     */
    Result<Object> removeMenus(String appId);

}

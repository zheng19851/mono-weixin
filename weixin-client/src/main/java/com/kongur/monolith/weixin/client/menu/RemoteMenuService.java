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

    /**
     * 刷新菜单缓存
     * 
     * @return
     */
    Result<Object> refreshMenus();
    
    /**
     * 创建菜单
     * 
     * @param appId 微信appId
     * @param menus
     * 
     * @return
     */
    Result<Object> createMenus(String appId, List<Menu> menus);

    /**
     * 创建菜单
     * 
     * @param menus
     * @return
     */
    Result<Object> createMenus(List<Menu> menus);

    /**
     * 删除所有菜单
     * 
     * @return
     */
    Result<Object> removeMenus();

    /**
     * 删除所有菜单
     * 
     * @return
     */
    Result<Object> removeMenus(String appId);
    
    /**
     * 获取所有菜单
     * 
     * @return
     */
    Result<List<Menu>> getMenus();

    /**
     * 获取菜单
     * 
     * @return
     */
    Result<Menu> getMenu(String menuId);

    /**
     * 更新菜单
     * 
     * @param menu
     * @return
     */
    Result<Object> updateMenu(Menu menu);

    /**
     * 删除菜单
     * 
     * @return
     */
    Result<Object> removeMenu(String menuId);

    /**
     * 创建菜单
     * 
     * @return
     */
    Result<Object> createMenu(Menu menu);

}

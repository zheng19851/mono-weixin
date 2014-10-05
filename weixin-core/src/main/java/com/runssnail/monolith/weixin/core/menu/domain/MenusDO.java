package com.runssnail.monolith.weixin.core.menu.domain;

import java.util.ArrayList;
import java.util.List;

import com.runssnail.monolith.common.DomainBase;

/**
 * 所有菜单对象
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class MenusDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -8782860705768827760L;
    private String            appId;

    private List<MenuDO>      menus;

    public boolean isEmpty() {
        return count() == 0;
    }

    /**
     * 一级菜单数量
     * 
     * @return
     */
    public int count() {
        return this.menus != null ? this.menus.size() : 0;
    }

    public MenusDO(List<MenuDO> menus) {
        this.menus = menus;
    }

    public List<MenuDO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDO> menus) {
        this.menus = menus;
    }

    public void addMenu(MenuDO menu) {
        if (this.menus == null) {
            this.menus = new ArrayList<MenuDO>();
        }
        this.menus.add(menu);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}

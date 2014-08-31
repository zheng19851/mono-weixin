package com.kongur.monolith.weixin.core.menu.domain;

import java.util.List;

import com.kongur.monolith.common.DomainBase;

public class MenuListDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 9011538048409714450L;
    private List<MenusDO>     menuList;

    public List<MenusDO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenusDO> menuList) {
        this.menuList = menuList;
    }

    public boolean isEmpty() {
        return this.menuList != null && this.menuList.isEmpty();
    }

}

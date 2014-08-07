package com.kongur.monolith.weixin.common.service;

import java.util.List;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.Menu;

/**
 * �˵����񣬸��ڲ�ϵͳ����
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public interface RemoteMenuService {

    /**
     * ˢ�²˵�����
     * 
     * @return
     */
    Result<Object> refreshMenus();

    /**
     * �����˵�
     * 
     * @param menus
     * @return
     */
    Result<Object> createMenus(List<Menu> menus);

    /**
     * ɾ�����в˵�
     * 
     * @return
     */
    Result<Object> removeMenus();

    /**
     * ��ȡ���в˵�
     * 
     * @return
     */
    Result<List<Menu>> getMenus();

    /**
     * ��ȡ�˵�
     * 
     * @return
     */
    Result<Menu> getMenu(String menuId);

    /**
     * ���²˵�
     * 
     * @param menu
     * @return
     */
    Result<Object> updateMenu(Menu menu);

    /**
     * ɾ���˵�
     * 
     * @return
     */
    Result<Object> removeMenu(String menuId);

    /**
     * �����˵�
     * 
     * @return
     */
    Result<Object> createMenu(Menu menu);

}

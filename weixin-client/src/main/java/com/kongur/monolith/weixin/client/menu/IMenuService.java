package com.kongur.monolith.weixin.client.menu;

import java.util.List;

import com.kongur.monolith.common.result.Result;

/**
 * �˵����񣬸��ڲ�ϵͳ����
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public interface IMenuService {

    void refresh(String appId);

    /**
     * �����˵�
     * 
     * @param appId ΢��appId
     * @param menus
     * @return
     */
    Result<Object> createMenus(String appId, List<Menu> menus);

    /**
     * ɾ�����в˵�
     * 
     * @param appId ΢��appId
     * @return
     */
    Result<Object> removeMenus(String appId);

}

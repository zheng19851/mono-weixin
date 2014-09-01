package com.kongur.monolith.weixin.core.menu.service;

import com.kongur.monolith.weixin.core.menu.domain.MenuDO;
import com.kongur.monolith.weixin.core.menu.domain.MenusDO;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;

/**
 * �˵��������
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public interface MenuManager {

    void refresh(String appId);

    void refresh();

    /**
     * ��ȡ���в˵�
     * 
     * @return
     */
    MenusDO getMenus();

    /**
     * �˵�����
     * 
     * @param menuId �˵�Id
     * @return
     */
    MenuDO getMenu(String menuId);

    /**
     * �����¼�KEY��ȡ�ظ�
     * 
     * @param eventKey
     * @return
     */
    ReplyDO getReplyByEventKey(String eventKey);

    /**
     * �Ƿ������ǰeventKey
     * 
     * @param eventKey
     * @return
     */
    boolean containsEventKey(String eventKey);

}

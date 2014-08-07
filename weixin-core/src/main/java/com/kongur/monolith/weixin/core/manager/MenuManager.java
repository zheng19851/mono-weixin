package com.kongur.monolith.weixin.core.manager;

import com.kongur.monolith.weixin.core.domain.MenuDO;
import com.kongur.monolith.weixin.core.domain.MenusDO;
import com.kongur.monolith.weixin.core.domain.ReplyDO;

/**
 * �˵��������
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public interface MenuManager {

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

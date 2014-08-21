package com.kongur.monolith.weixin.core.web.action;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.base.service.AccessTokenService;
import com.kongur.monolith.weixin.core.event.domain.SubscribeReplyDO;
import com.kongur.monolith.weixin.core.event.service.SubscribeReplyManager;
import com.kongur.monolith.weixin.core.menu.domain.MenusDO;
import com.kongur.monolith.weixin.core.menu.service.MenuManager;

/**
 * ����ǰ΢��ƽ̨�����ʺ���Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-18
 */
@Controller
public class ConfInfoManageAction {

    @Resource(name = "defaultAccessTokenService")
    private AccessTokenService    accessTokenService;

    @Autowired
    private MenuManager           menuManager;

    @Autowired
    private SubscribeReplyManager subscribeReplyManager;

    /**
     * �鿴��ǰaccess token
     * 
     * @param model
     * @return
     */
    @RequestMapping("get_access_token.htm")
    public String viewAccessToken(Model model) {

        String accessToken = accessTokenService.getAccessToken();

        model.addAttribute("accessToken", accessToken);

        return "weixin/get_access_token";
    }

    /**
     * ˢ�µ�ǰaccess token
     * 
     * @param model
     * @return
     */
    @RequestMapping("refresh_access_token.htm")
    public String refreshAccessToken(Model model) {

        Result<String> result = accessTokenService.refresh();

        model.addAttribute("result", result);

        return "weixin/refresh_access_token";
    }

    /**
     * �鿴�˵�
     * 
     * @param model
     * @return
     */
    @RequestMapping("get_menus.htm")
    public String getMenus(Model model) {

        MenusDO menus = menuManager.getMenus();

        model.addAttribute("menus", menus);

        return "weixin/get_menus";
    }

    /**
     * �鿴���Ļظ�����
     * 
     * @param model
     * @return
     */
    @RequestMapping("get_subscribe_reply.htm")
    public String getSubscribeReply(Model model) {

        SubscribeReplyDO subscribeReply = subscribeReplyManager.getSubscribeReply();

        model.addAttribute("reply", subscribeReply);

        return "weixin/get_subscribe_reply";
    }

    /**
     * ˢ�²˵�
     * 
     * @param model
     * @return
     */
    @RequestMapping("refresh_menus.htm")
    public String refreshMenus(Model model) {

        menuManager.refresh();

        return "success";
    }
    
    
    /**
     * ˢ�²˵�
     * 
     * @param model
     * @return
     */
    @RequestMapping("refresh_subscribe_reply.htm")
    public String refreshSubscribeReply(Model model) {

        subscribeReplyManager.refresh();

        return "success";
    }

}

package com.runssnail.monolith.weixin.core.web.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.base.service.AccessTokenService;
import com.runssnail.monolith.weixin.core.menu.domain.MenusDO;
import com.runssnail.monolith.weixin.core.menu.service.MenuManager;
import com.runssnail.monolith.weixin.core.reply.domain.ReplysDO;
import com.runssnail.monolith.weixin.core.reply.manager.SubscribeReplyManager;

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
    public String viewAccessToken(@RequestParam(value = "appId", required = false) String appId, Model model) {
        String accessToken = null;
        if (StringUtil.isNotBlank(appId)) {
            accessToken = accessTokenService.getAccessToken(appId);
        } else {
            accessToken = accessTokenService.getAccessToken();
        }

        model.addAttribute("accessToken", accessToken);

        return "weixin/get_access_token";
    }

    @RequestMapping("get_all_access_token.htm")
    public String viewAllAccessToken(Model model) {
        Map<String, String> accessTokens = accessTokenService.getAllAccessTokens();

        model.addAttribute("accessTokens", accessTokens);

        return "weixin/get_all_access_token";
    }

    /**
     * ˢ�µ�ǰaccess token
     * 
     * @param model
     * @return
     */
    @RequestMapping("refresh_all_access_token.htm")
    public String refreshAccessToken(Model model) {

        Result<Map<String, String>> result = accessTokenService.refresh();

        model.addAttribute("result", result);

        return "weixin/refresh_access_token";
    }

    /**
     * ˢ�µ�ǰaccess token
     * 
     * @param model
     * @return
     */
    @RequestMapping("refresh_access_token.htm")
    public String refresh(@RequestParam(value = "appId") String appId, Model model) {

        Result<String> result = accessTokenService.refresh(appId);
        model.addAttribute("result", result);

        return "weixin/refresh_curr_access_token";
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

        ReplysDO subscribeReply = subscribeReplyManager.getSubscribeReplys();

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

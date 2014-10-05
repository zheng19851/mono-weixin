package com.runssnail.monolith.weixin.core.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.client.user.IUserService;
import com.runssnail.monolith.weixin.core.mp.service.PublicNoInfoService;

/**
 * ≤‚ ‘Õ¯“≥ ⁄»®
 * 
 * @author zhengwei
 */
@Controller
public class OAuth2TestAction {

    @Autowired
    private IUserService   userSerivce;

    // @Autowired
    // private WeixinConfigService weixinConfigService;

    @Autowired
    private PublicNoInfoService publicNoInfoService;

    @RequestMapping("oauth/test.htm")
    public String test(@RequestParam(value = "code", required = false) String code,
                       @RequestParam(value = "openid", required = false) String openid,
                       @RequestParam(value = "appId", required = false) String appId, Model model) {

        StringBuilder message = new StringBuilder();
        message.append("code=").append(code);

        if (StringUtil.isBlank(appId)) {
            appId = publicNoInfoService.getDefaultAppId();
        }

        if (StringUtil.isNotBlank(code)) {
            Result<String> result = userSerivce.getOpenIdByOAuth2Code(appId, code);
            if (result.isSuccess()) {
                message.append(", openidFromWeixin=").append(result.getResult());
            } else {
                message.append(", getOpenIdError=").append(result.getResultInfo());
            }
        }

        model.addAttribute("message", message.toString());

        return "success";
    }

}

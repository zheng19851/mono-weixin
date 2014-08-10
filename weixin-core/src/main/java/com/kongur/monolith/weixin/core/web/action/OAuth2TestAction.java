package com.kongur.monolith.weixin.core.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eyeieye.skyjoo.util.StringUtil;
import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.service.RemoteUserService;

/**
 * ≤‚ ‘Õ¯“≥ ⁄»®
 * 
 * @author zhengwei
 */
@Controller
public class OAuth2TestAction {

    @Autowired
    private RemoteUserService userSerivce;

    @RequestMapping("oauth/test.htm")
    public String test(@RequestParam(value = "code", required = false) String code,
                       @RequestParam(value = "openid", required = false) String openid, Model model) {

        StringBuilder message = new StringBuilder();
        message.append("code=").append(code);

        if (StringUtil.isNotBlank(code)) {
            Result<String> result = userSerivce.getOpenIdByCode(code);
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

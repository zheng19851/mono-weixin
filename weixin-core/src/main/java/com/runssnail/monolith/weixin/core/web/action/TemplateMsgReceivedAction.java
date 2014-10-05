package com.runssnail.monolith.weixin.core.web.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.common.utils.HttpRequestUtils;

/**
 * 发送模板消息后，微信平台回调我们的url
 * 
 * @author zhengwei
 */
@Controller
public class TemplateMsgReceivedAction extends BaseAction {

    @RequestMapping("cgi/template_msg/callback.htm")
    public String callback(HttpServletRequest req) {

        String receivedMsg = HttpRequestUtils.readMsg(req);// 接收到的消息

        if (StringUtil.isNotBlank(receivedMsg)) {
            log.debug("template message callback, received msg->\n" + receivedMsg);
        }

        return null;
    }

}

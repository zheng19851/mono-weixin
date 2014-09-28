package com.kongur.monolith.weixin.core.web.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.common.utils.HttpRequestUtils;

/**
 * ����ģ����Ϣ��΢��ƽ̨�ص����ǵ�url
 * 
 * @author zhengwei
 */
@Controller
public class TemplateMsgReceivedAction extends BaseAction {

    @RequestMapping("cgi/template_msg/callback.htm")
    public String callback(HttpServletRequest req) {

        String receivedMsg = HttpRequestUtils.readMsg(req);// ���յ�����Ϣ

        if (StringUtil.isNotBlank(receivedMsg)) {
            log.debug("template message callback, received msg->\n" + receivedMsg);
        }

        return null;
    }

}

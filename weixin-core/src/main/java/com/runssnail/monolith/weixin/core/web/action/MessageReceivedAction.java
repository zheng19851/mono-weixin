package com.runssnail.monolith.weixin.core.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.base.service.SignatureValidator;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.message.service.MessageBuilder;
import com.runssnail.monolith.weixin.core.message.service.MessageProcessService;
import com.runssnail.monolith.weixin.core.message.service.MessageProcessServiceResolver;
import com.runssnail.monolith.weixin.core.mp.service.PublicNoInfoService;

/**
 * 微信消息接收服务
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Controller
public class MessageReceivedAction {

    private final Logger                  log = Logger.getLogger(getClass());

    // @Resource(name = "defaultMessageProcessServiceResolver")
    @Autowired
    private MessageProcessServiceResolver messageProcessServiceResolver;

    // @Resource(name = "defaultMessageBuilder")
    @Autowired
    private MessageBuilder                messageBuilder;

    @Autowired
    private SignatureValidator            signatureValidator;

    @Autowired
    private PublicNoInfoService           publicNoInfoService;

    /**
     * 接收微信推送消息
     * 
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return
     */
    @RequestMapping("message.htm")
    @ResponseBody
    public String messageReceived(@RequestParam(value = "signature", required = false) String signature,
                                  @RequestParam(value = "timestamp", required = false) String timestamp,
                                  @RequestParam(value = "nonce", required = false) String nonce,
                                  @RequestParam(value = "echostr", required = false) String echostr,
                                  @RequestParam(value = "appId", required = false) String appId, HttpServletRequest req) {

        if (log.isInfoEnabled()) {
            log.info("==============================message received==================================, reqParamsMap="
                     + req.getParameterMap());
        }

        if (log.isDebugEnabled()) {
            log.debug("signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr="
                      + echostr + ", appId=" + appId);
        }

        if (!publicNoInfoService.isEnabled(appId)) {
            log.error("the publicNo has not enabled, appId=" + appId);
            return null;
        }

        if (!signatureValidator.validate(appId, signature, timestamp, nonce)) {
            log.error("signature error. signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce
                      + ", echostr=" + echostr);
            return null;
        } else if (StringUtil.isNotBlank(echostr)) { // 不为空说明是第1次开发者验证
            return echostr;
        }

        Message msg = messageBuilder.build(appId, signature, timestamp, nonce, echostr, req);
        if (!msg.isValid()) {
            log.error("=============can not build valid message=============, msg=" + msg);
            return null;
        }

        MessageProcessService<Message> messageProcessService = messageProcessServiceResolver.resolve(msg);

        if (messageProcessService == null) {
            log.error("=============can not resolve MessageProcessService=============, msg=" + msg);
            return null;
        }

        Result<String> result = messageProcessService.process(msg);

        if (!result.isSuccess()) {
            log.error("=============process message error=============, errorInfo=" + result.getResultInfo() + ", msg="
                      + msg);
            return null;
        }

        if (log.isInfoEnabled()) {
            log.info("===========================message process successfully================================");
        }

        return result.getResult();
    }
}

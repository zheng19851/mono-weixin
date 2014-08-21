package com.kongur.monolith.weixin.core.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.base.service.SignatureValidator;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.service.MessageBuilder;
import com.kongur.monolith.weixin.core.message.service.MessageProcessService;
import com.kongur.monolith.weixin.core.message.service.MessageProcessServiceResolver;

/**
 * ΢����Ϣ���շ���
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Controller
public class MessageReceivedAction {

    private final Logger                  log = Logger.getLogger(getClass());

    @Resource(name = "defaultMessageProcessServiceResolver")
    private MessageProcessServiceResolver messageProcessServiceResolver;

    @Resource(name = "defaultMessageBuilder")
    private MessageBuilder                messageBuilder;

    @Autowired
    private SignatureValidator            signatureValidator;

    /**
     * ����΢��������Ϣ
     * 
     * @param signature ΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
     * @param timestamp ʱ���
     * @param nonce �����
     * @param echostr ����ַ���
     * @return
     */
    @RequestMapping("message.htm")
    @ResponseBody
    public String messageReceived(@RequestParam(value = "signature", required = false) String signature,
                                  @RequestParam(value = "timestamp", required = false) String timestamp,
                                  @RequestParam(value = "nonce", required = false) String nonce,
                                  @RequestParam(value = "echostr", required = false) String echostr,
                                  HttpServletRequest req) {

        if (log.isInfoEnabled()) {
            log.info("==============================message received==================================");
        }

        if (log.isDebugEnabled()) {
            log.debug("signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr="
                      + echostr);
        }

        if (!signatureValidator.validate(signature, timestamp, nonce)) {
            log.error("signature error. signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce
                      + ", echostr=" + echostr);
            return null;
        } else if (StringUtil.isNotBlank(echostr)) { // ��Ϊ��˵���ǵ�1�ο�������֤
            return echostr;
        }

        Message msg = messageBuilder.build(signature, timestamp, nonce, echostr, req);
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

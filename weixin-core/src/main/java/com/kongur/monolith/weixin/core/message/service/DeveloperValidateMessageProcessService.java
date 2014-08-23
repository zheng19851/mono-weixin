package com.kongur.monolith.weixin.core.message.service;

import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.message.domain.DeveloperValidateMessage;
import com.kongur.monolith.weixin.core.message.domain.EnumMessageType;
import com.kongur.monolith.weixin.core.message.domain.Message;

/**
 * ��������֤
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Service("developerValidateMessageProcessService")
public class DeveloperValidateMessageProcessService extends AbstractMessageProcessService<DeveloperValidateMessage> {

    @Override
    protected void doProcess(DeveloperValidateMessage msg, Result<String> result) {

        result.setResult(msg.getEchostr()); // ��֤ͨ����ԭ����������ַ���

    }

    @Override
    public boolean supports(Message msg) {
        return EnumMessageType.isDeveloperValidate(msg.getMsgType());
    }

}
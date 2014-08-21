package com.kongur.monolith.weixin.core.message.service.normal;

import com.kongur.monolith.weixin.core.message.domain.EnumMessageType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.normal.TextMessage;
import com.kongur.monolith.weixin.core.message.service.AbstractMessageProcessService;

/**
 * �ı���Ϣ�������
 * 
 * @author zhengwei
 * @date 2014-2-18
 */
// @Service("textMessageProcessService")
public class TextMessageProcessService extends AbstractMessageProcessService<TextMessage> {

    @Override
    public boolean supports(Message msg) {
        return EnumMessageType.isText(msg.getMsgType());
    }

}

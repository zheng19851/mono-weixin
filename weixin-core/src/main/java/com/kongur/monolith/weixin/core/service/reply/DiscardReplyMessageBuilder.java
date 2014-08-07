package com.kongur.monolith.weixin.core.service.reply;

import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.Reply;
import com.kongur.monolith.weixin.core.domain.message.Message;

/**
 * ���ظ��κ���Ϣ
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
@Service("discardReplyMessageBuilder")
public class DiscardReplyMessageBuilder extends AbstractReplyMessageBuilder<Reply> {

    @Override
    public boolean supports(Reply reply) {
        return false;
    }

    @Override
    protected void doBuild(Reply reply, Message msg, Result<String> result) {

        log.warn("the reply is discarded. reply=" + reply);

    }

}

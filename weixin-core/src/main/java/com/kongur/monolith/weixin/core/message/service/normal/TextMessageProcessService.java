package com.kongur.monolith.weixin.core.message.service.normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.message.domain.EnumMessageType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.normal.TextMessage;
import com.kongur.monolith.weixin.core.message.service.AbstractMessageProcessService;
import com.kongur.monolith.weixin.core.reply.domain.DefaultReplyDO;
import com.kongur.monolith.weixin.core.reply.domain.Reply;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;
import com.kongur.monolith.weixin.core.reply.manager.DefaultReplyManager;

/**
 * �ı���Ϣ�������
 * 
 * @author zhengwei
 * @date 2014-2-18
 */
@Service("textMessageProcessService")
@Order(1)
public class TextMessageProcessService extends AbstractMessageProcessService<TextMessage> {

    @Autowired
    private DefaultReplyManager defaultReplyManager;

    @Override
    public boolean supports(Message msg) {
        return EnumMessageType.isText(msg.getMsgType());
    }

    @Override
    protected Reply buildReply(TextMessage msg) {
        Reply reply = null;

        // �ж��Ƿ��ѯ�ݶ���Ϣ
        String content = msg.getContent();

        if (reply == null) {
            reply = createDefaultReply(msg);
        }

        return reply;
    }

    /**
     * ����Ĭ�ϵĻظ�
     * 
     * @param msg
     * @return
     */
    private Reply createDefaultReply(TextMessage msg) {
        DefaultReplyDO defaultReply = defaultReplyManager.getDefaultReply(msg.getAppId());
        if (defaultReply == null || StringUtil.isBlank(defaultReply.getContent())) {
            return null;
        }
        ReplyDO reply = new ReplyDO();
        reply.setType(EnumMessageType.TEXT.getValue());

        reply.setContent(defaultReply.getContent());

        return reply;
    }
}

package com.kongur.monolith.weixin.core.reply.service.unactive;

import java.util.Map;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.common.EnumMessageType;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;

/**
 * ͼ����Ϣ�ظ�������װ��
 * 
 * @author zhengwei
 * @date 2014��2��26��
 */
//@Service("newsReplyMessageBuilder")
public class NewsReplyMessageBuilder extends UnactiveVelocityReplyMessageBuilder<ReplyDO> {

    @Override
    public boolean supports(ReplyDO reply) {
        return super.supports(reply) && EnumMessageType.isNews(reply.getType());
    }

    @Override
    protected void validate(ReplyDO reply, Message msg, Result<String> result) {

        if (!reply.hasItems()) {
            result.setError("3001", "��������1��ͼ����Ϣ");
            return;
        }

    }

    @Override
    protected void buildModelParams(ReplyDO reply, Message msg, Map model) {
        // ignore
    }

}

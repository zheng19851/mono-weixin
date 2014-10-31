package com.runssnail.monolith.weixin.core.reply.builder.unactive;

import java.util.Map;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.common.EnumMessageType;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;
import com.runssnail.monolith.weixin.core.reply.domain.ReplyDO;

/**
 * ͼ����Ϣ�ظ�������װ��
 * 
 * @author zhengwei
 * @date 2014��2��26��
 */
//@Service("newsReplyMessageBuilder")
public class NewsReplyMessageBuilder extends UnactiveVelocityReplyMessageBuilder<ReplyDO> {

    @Override
    public boolean supports(Reply reply) {
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
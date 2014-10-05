package com.runssnail.monolith.weixin.core.reply.builder.unactive;

import java.util.Map;

import com.runssnail.monolith.weixin.client.common.EnumMessageType;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * �����ظ�ͼƬ��Ϣ����
 * 
 * @author zhengwei
 * @date 2014��2��26��
 */
public class ImageResourceReplyMessageBuilder extends UnactiveResourceReplyMessageBuilder<Reply> {

    @Override
    public boolean supports(Reply reply) {
        return super.supports(reply) && EnumMessageType.isImage(reply.getType());
    }

    @Override
    protected void buildModelParams(Reply reply, Message msg, Map model) {

        // ignore
        // model.put("toUser", msg.getFromUserName());
        // model.put("fromUser", msg.getToUserName());
        //
        // model.put("createTime", new Date().getTime());
        // // model.put("msgType", msg.getMsgType());
        //
        // model.put("reply", reply);
    }

}

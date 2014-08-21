package com.kongur.monolith.weixin.core.reply.service.unactive;

import java.util.Map;

import com.kongur.monolith.weixin.common.domain.dto.Reply;
import com.kongur.monolith.weixin.common.domain.enums.EnumReplyType;
import com.kongur.monolith.weixin.core.message.domain.Message;

/**
 * �����ظ�ͼƬ��Ϣ����
 * 
 * @author zhengwei
 * @date 2014��2��26��
 */
public class ImageResourceReplyMessageBuilder extends UnactiveResourceReplyMessageBuilder<Reply> {

    @Override
    public boolean supports(Reply reply) {
        return super.supports(reply) && EnumReplyType.isImage(reply.getType());
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

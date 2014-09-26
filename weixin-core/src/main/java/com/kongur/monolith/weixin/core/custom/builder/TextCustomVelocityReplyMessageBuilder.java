package com.kongur.monolith.weixin.core.custom.builder;

import com.kongur.monolith.weixin.core.custom.domain.CustomReply;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * �ͷ��ı���Ϣ���ݴ����ӿ�
 * 
 * @author zhengwei
 */
public class TextCustomVelocityReplyMessageBuilder extends CustomVelocityReplyMessageBuilder<CustomReply> {

    @Override
    public boolean supports(Reply reply) {
        return super.supports(reply) && reply.isText();
    }

}

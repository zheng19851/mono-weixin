package com.kongur.monolith.weixin.core.reply.service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * �����ظ���Ϣ��Ҳ���Դ����������͵���Ϣ
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public interface ReplyMessageBuilder<R extends Reply> {

    /**
     * �Ƿ�֧�ִ���ǰ�ظ���Ϣ
     * 
     * @param reply
     * @return
     */
    boolean supports(Reply reply);

    /**
     * �����ظ���Ϣ��Ҳ���Դ����������͵���Ϣ
     * 
     * @param reply
     * @return result�������Ƿ��ظ�ƽ̨������
     */
    Result<String> build(R reply, Message msg);

}

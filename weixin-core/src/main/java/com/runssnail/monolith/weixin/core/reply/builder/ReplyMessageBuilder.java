package com.runssnail.monolith.weixin.core.reply.builder;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

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
     * �����ظ���Ϣ���յ��û���Ϣ���Զ��ظ�(������)
     * 
     * @param reply
     * @param msg ΢��ƽ̨���͹�������Ϣ
     * @return result�������Ƿ��ظ�ƽ̨������
     */
    Result<String> build(R reply, Message msg);

    /**
     * �����ظ���Ϣ�����������ظ�
     * 
     * @param reply
     * @return
     */
    Result<String> build(R reply);

}

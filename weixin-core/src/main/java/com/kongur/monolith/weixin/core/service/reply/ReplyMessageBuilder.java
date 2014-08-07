package com.kongur.monolith.weixin.core.service.reply;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.Reply;
import com.kongur.monolith.weixin.core.common.Ordered;
import com.kongur.monolith.weixin.core.domain.message.Message;

/**
 * �����ظ���Ϣ��Ҳ���Դ����������͵���Ϣ
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public interface ReplyMessageBuilder<R extends Reply> extends Ordered {

    /**
     * �Ƿ�֧�ִ���ǰ�ظ���Ϣ
     * 
     * @param reply
     * @return
     */
    boolean supports(R reply);

    /**
     * �����ظ���Ϣ��Ҳ���Դ����������͵���Ϣ
     * 
     * @param reply
     * @return result�������Ƿ��ظ�ƽ̨������
     */
    Result<String> build(R reply, Message msg);

}

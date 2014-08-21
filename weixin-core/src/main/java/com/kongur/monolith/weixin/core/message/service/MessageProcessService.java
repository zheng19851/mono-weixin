package com.kongur.monolith.weixin.core.message.service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.Ordered;
import com.kongur.monolith.weixin.core.message.domain.Message;

/**
 * ��Ϣ�������
 * <p>
 * �����ƽ̨�յ�����Ϣ
 * </p>
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface MessageProcessService<M extends Message> extends Ordered {

    /**
     * �����յ�����Ϣ����
     * 
     * @param msg
     * @return resultΪ���ظ�ƽ̨����Ӧ����
     */
    Result<String> process(M msg);

    /**
     * �Ƿ�֧�ִ���ǰ��Ϣ
     * 
     * @param msg
     * @return
     */
    boolean supports(Message msg);

}

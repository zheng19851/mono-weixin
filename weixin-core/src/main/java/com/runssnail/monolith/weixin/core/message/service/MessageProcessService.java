package com.runssnail.monolith.weixin.core.message.service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.message.domain.Message;

/**
 * ��Ϣ�������
 * <p>
 * �����ƽ̨�յ�����Ϣ
 * </p>
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface MessageProcessService<M extends Message> {

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

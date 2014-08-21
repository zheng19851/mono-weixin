package com.kongur.monolith.weixin.core.message.service;

import com.kongur.monolith.weixin.core.message.domain.Message;

/**
 * ��ѯ����Ҫ�����Ϣ�������
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface MessageProcessServiceResolver {

    /**
     * ��ȡ��Ӧ����Ϣ�������Ⱥ�˳���ĸ���ƥ�䵽��ֱ�ӷ���
     * 
     * @param msg
     * @return
     */
    MessageProcessService<Message> resolve(Message msg);

    /**
     * ���MessageProcessService
     * 
     * @param service
     */
    boolean addMessageProcessService(MessageProcessService<Message> service);

    /**
     * ɾ��MessageProcessService
     * 
     * @param service
     * @return
     */
    boolean removeMessageProcessService(MessageProcessService<Message> service);

}

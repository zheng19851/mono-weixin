package com.kongur.monolith.weixin.core.event.service;

import com.kongur.monolith.weixin.core.event.domain.SubscribeReplyDO;

/**
 * ��ע�¼���Ϣ�ظ����ù���
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public interface SubscribeReplyManager {

    /**
     * ����
     * 
     * @param reply
     * @return
     */
    String create(SubscribeReplyDO reply);

    /**
     * ��ѯ
     * 
     * @param id
     * @return
     */
    SubscribeReplyDO getSubscribeReply();

    /**
     * �޸�
     * 
     * @param reply
     * @return
     */
    boolean update(SubscribeReplyDO reply);

    /**
     * ɾ��
     * 
     * @param id
     * @return
     */
    boolean remove();

    void refresh();

}

package com.kongur.monolith.weixin.core.reply.manager;

import com.kongur.monolith.weixin.core.reply.domain.ReplysDO;
import com.kongur.monolith.weixin.core.reply.domain.SubscribeReplyDO;

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
     * @param appId 
     * 
     * @param id
     * @return
     */
    SubscribeReplyDO getSubscribeReply(String appId);
    
    ReplysDO getSubscribeReplys();

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

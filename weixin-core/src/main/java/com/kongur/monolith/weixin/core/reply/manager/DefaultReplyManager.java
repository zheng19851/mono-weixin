package com.kongur.monolith.weixin.core.reply.manager;

import com.kongur.monolith.weixin.core.reply.domain.DefaultReplyDO;


/**
 * ����ظ�����
 * 
 * @author zhengwei
 */
public interface DefaultReplyManager {

    /**
     * ����ָ�����
     * 
     * @return
     */
    DefaultReplyDO getDefaultReply(String appId);

    /**
     * �޸�
     * 
     * @param errorReply
     * @return
     */
    boolean update(DefaultReplyDO errorReply);

    /**
     * ˢ��
     */
    void refresh();

}

package com.kongur.monolith.weixin.core.reply.manager;

import com.kongur.monolith.weixin.core.reply.domain.ErrorReplyDO;


/**
 * ����ظ�����
 * 
 * @author zhengwei
 */
public interface ErrorReplyManager {

    /**
     * ����ָ�����
     * 
     * @return
     */
    ErrorReplyDO getErrorReply();

    /**
     * ˢ��
     */
    void refresh();

}

package com.kongur.monolith.weixin.core.event.service;

import com.kongur.monolith.weixin.core.event.domain.ErrorReplyDO;


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

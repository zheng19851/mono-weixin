package com.kongur.monolith.weixin.core.reply.manager;

import com.kongur.monolith.weixin.core.reply.domain.DefaultReplyDO;


/**
 * Ĭ�ϻظ��������û��������ݵ����ںţ����Ҳ�����Ӧ�ظ�����ʱ��Ĭ�Ϸ��ص�����
 * 
 * @author zhengwei
 */
public interface DefaultReplyManager {

    /**
     * ����ָ�����
     * 
     * @return
     */
    DefaultReplyDO getErrorReply();

    /**
     * ˢ��
     */
    void refresh();

}

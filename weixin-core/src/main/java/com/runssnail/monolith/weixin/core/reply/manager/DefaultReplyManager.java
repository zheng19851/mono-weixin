package com.runssnail.monolith.weixin.core.reply.manager;

import com.runssnail.monolith.weixin.core.reply.domain.DefaultReplyDO;

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
     * ˢ��
     */
    void refresh();

}

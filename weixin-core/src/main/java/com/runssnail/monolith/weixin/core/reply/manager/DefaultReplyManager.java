package com.runssnail.monolith.weixin.core.reply.manager;

import com.runssnail.monolith.weixin.core.reply.domain.DefaultReplyDO;

/**
 * 错误回复管理
 * 
 * @author zhengwei
 */
public interface DefaultReplyManager {

    /**
     * 错误恢复内容
     * 
     * @return
     */
    DefaultReplyDO getDefaultReply(String appId);

    /**
     * 刷新
     */
    void refresh();

}

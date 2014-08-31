package com.kongur.monolith.weixin.core.reply.manager;

import com.kongur.monolith.weixin.core.reply.domain.DefaultReplyDO;


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
     * 修改
     * 
     * @param errorReply
     * @return
     */
    boolean update(DefaultReplyDO errorReply);

    /**
     * 刷新
     */
    void refresh();

}

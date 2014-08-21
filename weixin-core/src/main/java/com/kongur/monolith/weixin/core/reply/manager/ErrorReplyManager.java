package com.kongur.monolith.weixin.core.reply.manager;

import com.kongur.monolith.weixin.core.reply.domain.ErrorReplyDO;


/**
 * 错误回复管理
 * 
 * @author zhengwei
 */
public interface ErrorReplyManager {

    /**
     * 错误恢复内容
     * 
     * @return
     */
    ErrorReplyDO getErrorReply();

    /**
     * 刷新
     */
    void refresh();

}

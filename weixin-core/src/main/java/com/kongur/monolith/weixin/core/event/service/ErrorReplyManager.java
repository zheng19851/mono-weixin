package com.kongur.monolith.weixin.core.event.service;

import com.kongur.monolith.weixin.core.event.domain.ErrorReplyDO;


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

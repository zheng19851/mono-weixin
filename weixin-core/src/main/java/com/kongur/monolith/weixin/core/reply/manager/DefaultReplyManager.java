package com.kongur.monolith.weixin.core.reply.manager;

import com.kongur.monolith.weixin.core.reply.domain.DefaultReplyDO;


/**
 * 默认回复管理，当用户发送内容到公众号，但找不到对应回复内容时，默认返回的内容
 * 
 * @author zhengwei
 */
public interface DefaultReplyManager {

    /**
     * 错误恢复内容
     * 
     * @return
     */
    DefaultReplyDO getErrorReply();

    /**
     * 刷新
     */
    void refresh();

}

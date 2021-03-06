package com.runssnail.monolith.weixin.core.reply.manager;

import com.runssnail.monolith.weixin.core.reply.domain.ReplysDO;
import com.runssnail.monolith.weixin.core.reply.domain.SubscribeReplyDO;

/**
 * 关注事件消息回复设置管理
 * 
 * @author zhengwei
 * @date 2014年2月21日
 */
public interface SubscribeReplyManager {

    /**
     * 查询
     * 
     * @param appId
     * @param id
     * @return
     */
    SubscribeReplyDO getSubscribeReply(String appId);

    ReplysDO getSubscribeReplys();

    void refresh();

}

package com.kongur.monolith.weixin.core.reply.manager;

import com.kongur.monolith.weixin.core.reply.domain.ReplysDO;
import com.kongur.monolith.weixin.core.reply.domain.SubscribeReplyDO;

/**
 * 关注事件消息回复设置管理
 * 
 * @author zhengwei
 * @date 2014年2月21日
 */
public interface SubscribeReplyManager {

    /**
     * 创建
     * 
     * @param reply
     * @return
     */
    String create(SubscribeReplyDO reply);

    /**
     * 查询
     * @param appId 
     * 
     * @param id
     * @return
     */
    SubscribeReplyDO getSubscribeReply(String appId);
    
    ReplysDO getSubscribeReplys();

    /**
     * 修改
     * 
     * @param reply
     * @return
     */
    boolean update(SubscribeReplyDO reply);

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    boolean remove();
    
    void refresh();

}

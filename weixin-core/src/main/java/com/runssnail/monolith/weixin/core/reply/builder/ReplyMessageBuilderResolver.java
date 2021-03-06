package com.runssnail.monolith.weixin.core.reply.builder;

import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * 查找符合要求的处理服务
 * 
 * @author zhengwei
 * @date 2014年2月21日
 */
public interface ReplyMessageBuilderResolver<R extends Reply> {

    /**
     * 查找
     * 
     * @param reply
     * @return
     */
    ReplyMessageBuilder<R> resolve(R reply);

}

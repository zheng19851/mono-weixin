package com.kongur.monolith.weixin.core.reply.service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * 创建回复消息，也可以创建主动发送的消息
 * 
 * @author zhengwei
 * @date 2014年2月21日
 */
public interface ReplyMessageBuilder<R extends Reply> {

    /**
     * 是否支持处理当前回复消息
     * 
     * @param reply
     * @return
     */
    boolean supports(R reply);

    /**
     * 创建回复消息，也可以创建主动发送的消息
     * 
     * @param reply
     * @return result包含的是返回给平台的内容
     */
    Result<String> build(R reply, Message msg);

}

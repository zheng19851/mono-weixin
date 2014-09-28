package com.kongur.monolith.weixin.core.reply.builder;

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
    boolean supports(Reply reply);

    /**
     * 创建回复消息，收到用户消息后，自动回复(被动的)
     * 
     * @param reply
     * @param msg 微信平台推送过来的消息
     * @return result包含的是返回给平台的内容
     */
    Result<String> build(R reply, Message msg);

    /**
     * 创建回复消息，可主被动回复
     * 
     * @param reply
     * @return
     */
    Result<String> build(R reply);

}

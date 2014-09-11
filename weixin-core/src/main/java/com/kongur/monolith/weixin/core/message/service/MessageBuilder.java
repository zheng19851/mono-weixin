package com.kongur.monolith.weixin.core.message.service;

import javax.servlet.http.HttpServletRequest;

import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.message.domain.features.Features;

/**
 * 创建消息对象
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface MessageBuilder {

    /**
     * 组装消息对象
     * 
     * @param req
     * @return
     */
    Message<Features> build(HttpServletRequest req);

    /**
     * 组装消息对象
     * 
     * @param appId 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param req
     * @return
     */
    Message<Features> build( String appId,String signature, String timestamp, String nonce, String echostr, HttpServletRequest req);

}

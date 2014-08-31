package com.kongur.monolith.weixin.core.message.service;

import javax.servlet.http.HttpServletRequest;

import com.kongur.monolith.weixin.core.message.domain.Message;

/**
 * ������Ϣ����
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface MessageBuilder {

    /**
     * ��װ��Ϣ����
     * 
     * @param req
     * @return
     */
    Message build(HttpServletRequest req);

    /**
     * ��װ��Ϣ����
     * 
     * @param appId 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param req
     * @return
     */
    Message build( String appId,String signature, String timestamp, String nonce, String echostr, HttpServletRequest req);

}

package com.kongur.monolith.weixin.core.service.message;

import javax.servlet.http.HttpServletRequest;

import com.kongur.monolith.weixin.core.domain.message.Message;

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
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param req
     * @return
     */
    Message build(String signature, String timestamp, String nonce, String echostr, HttpServletRequest req);

}

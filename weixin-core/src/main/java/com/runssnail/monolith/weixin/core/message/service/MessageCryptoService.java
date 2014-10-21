package com.runssnail.monolith.weixin.core.message.service;

import com.runssnail.monolith.weixin.core.message.crypto.AesException;

/**
 * 消息加解密服务
 * 
 * @author zhengwei
 */
public interface MessageCryptoService {

    /**
     * 加密
     * 
     * @param encryptType 加密方式
     * @param replyMsg 回复微信平台的xml明文消息
     * @param timeStamp 时间戳
     * @param nonce 随机数
     * @return 包含密文的xml数据
     * @throws AesException
     */
    String encryptMsg(String appId, String encryptType, String replyMsg, String timeStamp, String nonce)
                                                                                                        throws AesException;

    /**
     * 解密
     * 
     * @param encryptType 解密方式
     * @param msgSignature 消息签名
     * @param timeStamp 时间戳
     * @param nonce 随机数
     * @param postData 微信平台推送过来的xml消息
     * @param receivedMsg
     * @return
     * @throws AesException
     */
    String decryptMsg(String appId, String encryptType, String msgSignature, String timeStamp, String nonce,
                      String postData) throws AesException;

}

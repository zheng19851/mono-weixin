package com.kongur.monolith.weixin.core.base.service;

/**
 * signature 验证器
 *
 * @author zhengwei
 * @date 2014-2-14
 */
public interface SignatureValidator {

    /**
     * 验证签名，用默认的appid设置的token来验证
     *
     * @param signature 加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    boolean validate(String signature, String timestamp, String nonce);

    /**
     * 验证签名
     *
     * @param appId appId
     * @param signature 加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    boolean validate(String appId, String signature, String timestamp, String nonce);

}

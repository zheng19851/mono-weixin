package com.runssnail.monolith.weixin.core.base.service;

/**
 * signature ��֤��
 *
 * @author zhengwei
 * @date 2014-2-14
 */
public interface SignatureValidator {

    /**
     * ��֤ǩ������Ĭ�ϵ�appid���õ�token����֤
     *
     * @param signature ����ǩ��
     * @param timestamp ʱ���
     * @param nonce �����
     * @return
     */
    boolean validate(String signature, String timestamp, String nonce);

    /**
     * ��֤ǩ��
     *
     * @param appId appId
     * @param signature ����ǩ��
     * @param timestamp ʱ���
     * @param nonce �����
     * @return
     */
    boolean validate(String appId, String signature, String timestamp, String nonce);

}

package com.kongur.monolith.weixin.core.service;


/**
 * 
 * signature ��֤��
 *
 *@author zhengwei
 *
 *@date 2014-2-14	
 *
 */
public interface SignatureValidator {

    /**
     * ��֤ǩ��
     *
     * @param signature ����ǩ��
     * @param timestamp ʱ���
     * @param nonce �����
     * @return
     */
    boolean validate(String signature, String timestamp, String nonce);
    
}


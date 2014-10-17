package com.runssnail.monolith.weixin.core.message.service;

/**
 * ��Ϣ�ӽ��ܷ���
 * 
 * @author zhengwei
 */
public interface MessageCryptoService {

    /**
     * ����
     * 
     * @param encryptType ���ܷ�ʽ
     * @param replyMsg �ظ�΢��ƽ̨��xml������Ϣ
     * @param timeStamp ʱ���
     * @param nonce �����
     * @return �������ĵ�xml����
     * @throws AesException
     */
    String encryptMsg(String appId, String encryptType, String replyMsg, String timeStamp, String nonce) throws AesException;

    /**
     * ����
     * 
     * @param encryptType ���ܷ�ʽ
     * @param msgSignature ��Ϣǩ��
     * @param timeStamp ʱ���
     * @param nonce �����
     * @param postData ΢��ƽ̨���͹�����xml��Ϣ
     * @param receivedMsg
     * @return
     * @throws AesException
     */
    String decryptMsg(String appId, String encryptType, String msgSignature, String timeStamp, String nonce,
                      String postData) throws AesException;

}

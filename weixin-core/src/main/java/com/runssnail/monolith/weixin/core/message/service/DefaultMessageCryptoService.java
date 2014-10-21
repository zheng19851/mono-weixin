package com.runssnail.monolith.weixin.core.message.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.weixin.core.message.EnumEncryptType;
import com.runssnail.monolith.weixin.core.message.crypto.AesException;
import com.runssnail.monolith.weixin.core.message.crypto.WXBizMsgCrypt;
import com.runssnail.monolith.weixin.core.mp.service.PublicNoInfoService;

/**
 * 消息加解密服务
 * 
 * @author zhengwei
 */
@Service("messageCryptService")
public class DefaultMessageCryptoService implements MessageCryptoService {

    private final Logger               log    = Logger.getLogger(getClass());

    @Autowired
    private PublicNoInfoService        publicNoInfoService;

    /**
     * key = appid
     */
    private Map<String, WXBizMsgCrypt> crypts = new ConcurrentHashMap<String, WXBizMsgCrypt>();

    @PostConstruct
    public void init() throws AesException {

    }

    @Override
    public String encryptMsg(String appId, String encryptType, String replyMsg, String timeStamp, String nonce)
                                                                                                               throws AesException {

        if (log.isDebugEnabled()) {
            log.debug("encryptMsg, appId=" + appId + ", replyMsg=" + replyMsg + ", timeStamp=" + timeStamp + ", nonce="
                      + nonce);
        }
        if (!this.publicNoInfoService.exists(appId)) {
            throw new RuntimeException("appid not exists");
        }

        if (!isEncryptEnabled(encryptType)) {
            return replyMsg;
        }

        WXBizMsgCrypt crypt = getCrypt(appId);

        String encryptedMsg = crypt.encryptMsg(replyMsg, timeStamp, nonce);

        if (log.isDebugEnabled()) {
            log.debug("encryptMsg success, encryptedMsg=" + encryptedMsg);
        }

        return encryptedMsg;
    }

    private WXBizMsgCrypt getCrypt(String appId) throws AesException {

        WXBizMsgCrypt crypt = this.crypts.get(appId);

        if (crypt == null) {
            crypt = new WXBizMsgCrypt(publicNoInfoService.getTokenByAppId(appId),
                                      publicNoInfoService.getEncodingAesKey(appId), appId);

            this.crypts.put(appId, crypt);
        }

        return crypt;
    }

    @Override
    public String decryptMsg(String appId, String encryptType, String msgSignature, String timeStamp, String nonce,
                             String postData) throws AesException {

        if (log.isDebugEnabled()) {
            log.debug("decryptMsg, appId=" + appId + ", msgSignature=" + msgSignature + ", timeStamp=" + timeStamp
                      + ", nonce=" + nonce + ", postData=" + postData);
        }

        if (!this.publicNoInfoService.exists(appId)) {
            throw new RuntimeException("appid not exists");
        }

        if (!isEncryptEnabled(encryptType)) {
            return postData;
        }

        WXBizMsgCrypt crypt = getCrypt(appId);

        String decryptedMsg = crypt.decryptMsg(msgSignature, timeStamp, nonce, postData);

        if (log.isDebugEnabled()) {
            log.debug("decryptMsg success, decryptedMsg=" + decryptedMsg);
        }

        return decryptedMsg;
    }

    private boolean isEncryptEnabled(String encryptType) {

        return EnumEncryptType.isEncryptEnabled(encryptType);
    }

}

package com.kongur.monolith.weixin.core.base.service.impl;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.base.service.SignatureValidator;
import com.kongur.monolith.weixin.core.mp.service.PublicNoInfoService;

/**
 * ΢��ǩ����֤��
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
@Service("signatureValidator")
public class DefaultSignatureValidator implements SignatureValidator {

    private final Logger        log             = Logger.getLogger(getClass());

    @Autowired
    private PublicNoInfoService publicNoInfoService;

    /**
     * ΢��token
     */
    // @Value("${weixin.token}")
    // private String token;

    /**
     * �Ƿ�ر���֤, Ĭ��Ϊ������֤
     */
    @Value("${weixin.token.validate.disable}")
    private boolean             disableValidate = false;

    @PostConstruct
    public void init() {
        // if (StringUtil.isBlank(this.token)) {
        // throw new IllegalArgumentException("the token can not be blank.");
        // }
        //
        // if (log.isDebugEnabled()) {
        // log.debug("token=" + this.token + ", disableValidate=" + this.disableValidate);
        // }

    }

    /**
     * ����/У���������£�
     * <ul>
     * <li>1. ��token��timestamp��nonce�������������ֵ�������</li>
     * <li>2. �����������ַ���ƴ�ӳ�һ���ַ�������sha1����</li>
     * <li>3.�����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��</li>
     * <ul>
     */
    @Override
    public boolean validate(String signature, String timestamp, String nonce) {

        return this.doValidate(publicNoInfoService.getDefaultToken(), signature, timestamp, nonce);
    }

    /**
     * ��֤
     * 
     * @param token ΢��ƽ̨���õ�token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    private boolean doValidate(String token, String signature, String timestamp, String nonce) {

        if (disableValidate) {
            return true;
        }

        if (StringUtil.isBlank(signature) || StringUtil.isBlank(timestamp) || StringUtil.isBlank(nonce)) {
            log.error("validate signature error, the validate arguments can not be blank. signature=" + signature
                      + ", timestamp=" + timestamp + ", nonce=" + nonce + ", token=" + token);
            return false;
        }

        String[] signatureArray = { token, timestamp, nonce };

        Arrays.sort(signatureArray); // ��Ȼ����

        // ����ǰ��ǩ��
        String internalSignature = StringUtil.join(signatureArray);

        // ���ܺ��ǩ��
        String encodedInternalSignature = DigestUtils.sha1Hex(internalSignature);

        return signature.equals(encodedInternalSignature);
    }

    @Override
    public boolean validate(String appId, String signature, String timestamp, String nonce) {

        if (log.isDebugEnabled()) {
            log.debug("validate signature, the validate arguments can not be blank. signature=" + signature
                      + ", timestamp=" + timestamp + ", nonce=" + nonce + ", appId=" + appId);
        }

        String token = publicNoInfoService.getTokenByAppId(appId);

        if (StringUtil.isBlank(token)) {
            log.error("the token is not exists, appId=" + appId);
            return false;
        }

        return this.doValidate(token, signature, timestamp, nonce);

    }

}

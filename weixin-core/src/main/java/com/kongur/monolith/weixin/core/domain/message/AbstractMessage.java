package com.kongur.monolith.weixin.core.domain.message;

import java.util.Map;

import com.kongur.monolith.common.DomainBase;
import com.kongur.monolith.lang.StringUtil;

/**
 * ��Ϣ������
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public abstract class AbstractMessage extends DomainBase implements Message {

    /**
     * 
     */
    private static final long   serialVersionUID = -2293661716502114332L;

    /**
     * ΢�ż���ǩ��
     */
    private String              signature;

    /**
     * ʱ���
     */
    private String              timestamp;

    /**
     * �����
     */
    private String              nonce;

    /**
     * ��Ϣ����
     */
    private String              msgType;

    /**
     * ҵ������
     */
    private Map<String, Object> params           = null;

    public AbstractMessage(String signature, String timestamp, String nonce) {
        this(signature, timestamp, nonce, null);
    }

    public AbstractMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        this.signature = signature;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.params = params;
    }

    public String getMsgType() {

        String msgType = this.msgType;
        if (StringUtil.isBlank(msgType)) {
            msgType = this.getString("MsgType");
        }
        return msgType;
    }

    @Override
    public String getMsgId() {
        return this.getString("MsgId");
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public Map<String, Object> getParams() {
        return this.params;
    }

    @Override
    public String getString(String key) {
        Object obj = getParam(key);

        return obj != null ? obj.toString() : null;
    }

    @Override
    public Object getParam(String key) {
        return this.params.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return this.params != null && this.params.containsKey(key);
    }

    @Override
    public String getFromUserName() {
        return this.getString("FromUserName");
    }

    @Override
    public String getToUserName() {

        return this.getString("ToUserName");
    }

    @Override
    public long getCreateTime() {
        String createTime = this.getString("CreateTime");
        return StringUtil.isNotBlank(createTime) ? Long.valueOf(createTime) : -1;
    }

    @Override
    public boolean isValid() {
        return true;
    }

}

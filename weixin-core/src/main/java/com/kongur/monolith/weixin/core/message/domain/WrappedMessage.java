package com.kongur.monolith.weixin.core.message.domain;

import java.util.Map;

import com.kongur.monolith.common.DomainBase;
import com.kongur.monolith.common.UUIDGenerator;
import com.kongur.monolith.weixin.core.message.domain.features.Features;

/**
 * �ڲ���message����
 * 
 * @author zhengwei
 * @date 2014-4-10
 */
public class WrappedMessage extends DomainBase implements Message<Features> {

    /**
     * 
     */
    private static final long serialVersionUID = -688201228345365953L;

    /**
     * �ڲ���ϢID
     */
    private String            id;

    private Message<Features>           message;

    public WrappedMessage(Message<Features> message) {
        this(UUIDGenerator.generate(), message);
    }

    /**
     * @param id �ڲ���ϢID
     * @param message ���յ���ʵ����Ϣ
     */
    public WrappedMessage(String id, Message<Features> message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String getMsgId() {
        return message.getMsgId();
    }

    @Override
    public String getMsgType() {
        return message.getMsgType();
    }

    @Override
    public String getSignature() {
        return message.getSignature();
    }

    @Override
    public String getTimestamp() {
        return message.getTimestamp();
    }

    @Override
    public String getNonce() {
        return message.getNonce();
    }

    @Override
    public String getFromUserName() {
        return message.getFromUserName();
    }

    @Override
    public String getToUserName() {
        return message.getToUserName();
    }

    @Override
    public long getCreateTime() {
        return message.getCreateTime();
    }

    @Override
    public Map<String, Object> getParams() {
        return message.getParams();
    }

    @Override
    public String getString(String key) {
        return message.getString(key);
    }

    @Override
    public Object getParam(String key) {
        return message.getParam(key);
    }

    @Override
    public boolean containsKey(String key) {
        return message.containsKey(key);
    }

    @Override
    public boolean isValid() {
        return message.isValid();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getAppId() {
        return message.getAppId();
    }

    @Override
    public Features getFeatures() {
        return message.getFeatures();
    }

}

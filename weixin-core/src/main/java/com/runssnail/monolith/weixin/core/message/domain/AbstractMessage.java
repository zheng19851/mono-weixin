package com.runssnail.monolith.weixin.core.message.domain;

import java.util.Map;

import com.runssnail.monolith.common.DomainBase;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.message.domain.features.Features;

/**
 * 消息抽象类
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public abstract class AbstractMessage<F extends Features> extends DomainBase implements Message<F> {

    /**
     * 
     */
    private static final long   serialVersionUID = -2293661716502114332L;

    private String              appId;

    /**
     * 微信加密签名
     */
    private String              signature;

    /**
     * 时间戳
     */
    private String              timestamp;

    /**
     * 随机数
     */
    private String              nonce;

    /**
     * 消息类型
     */
    private String              msgType;

    /**
     * 业务数据
     */
    private Map<String, Object> params           = null;

    private F                   features;

    public AbstractMessage(String signature, String timestamp, String nonce) {
        this(null, signature, timestamp, nonce, null);
    }

    public AbstractMessage(String appId, String signature, String timestamp, String nonce) {
        this(appId, signature, timestamp, nonce, null);
    }

    public AbstractMessage(String signature, String timestamp, String nonce, Map<String, Object> params) {
        this(null, signature, timestamp, nonce, params);
    }

    public AbstractMessage(String appId, String signature, String timestamp, String nonce, Map<String, Object> params) {
        this.appId = appId;
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

    public int getInt(String key) {
        return Integer.valueOf(getString(key));
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public F getFeatures() {
        return this.features;
    }

    public void setFeatures(F features) {
        this.features = features;
    }

}

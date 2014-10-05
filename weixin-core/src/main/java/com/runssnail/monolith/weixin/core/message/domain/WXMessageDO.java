package com.runssnail.monolith.weixin.core.message.domain;

import java.util.Date;

import com.runssnail.monolith.common.DomainBase;
import com.runssnail.monolith.weixin.core.message.domain.features.Features;
;

/***
 * 微信信息
 */
public class WXMessageDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -232982866442196441L;

    private Long              id;

    private String            appId;

    private String            msgId;

    /**
     * 消息类型
     * 
     * @see EnumMessageType
     */
    private String            msgType;

    private String            fromUser;

    private String            toUser;

    private Long              createTime;

    /**
     * 消息特性，类型不同，存放的数据也不同
     */
    private String            features;

    private Integer           status;

    private Date              gmtCreate;

    private Date              gmtModify;

    public WXMessageDO(Message<Features> msg) {
        this.appId = msg.getAppId();
        this.msgId = msg.getMsgId();
        this.msgType = msg.getMsgType();
        this.fromUser = msg.getFromUserName();
        this.toUser = msg.getToUserName();
        this.createTime = msg.getCreateTime();
        this.status = 0;

        Features features = msg.getFeatures();
        if (features != null) {
            this.features = msg.getFeatures().buildJson();
        }
    }

    public WXMessageDO() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    /***
     * Get id
     */
    public Long getId() {
        return id;
    }

    /***
     * Set id
     * 
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /***
     * Get appId
     */
    public String getAppId() {
        return appId;
    }

    /***
     * Set appId
     * 
     * @param appId appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /***
     * Get msgId
     */
    public String getMsgId() {
        return msgId;
    }

    /***
     * Set msgId
     * 
     * @param msgId msgId
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /***
     * Get msgType
     */
    public String getMsgType() {
        return msgType;
    }

    /***
     * Set msgType
     * 
     * @param msgType msgType
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /***
     * Get fromUser
     */
    public String getFromUser() {
        return fromUser;
    }

    /***
     * Set fromUser
     * 
     * @param fromUser fromUser
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /***
     * Get toUser
     */
    public String getToUser() {
        return toUser;
    }

    /***
     * Set toUser
     * 
     * @param toUser toUser
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /***
     * Get gmtCreate
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /***
     * Set gmtCreate
     * 
     * @param gmtCreate gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /***
     * Get gmtModify
     */
    public Date getGmtModify() {
        return gmtModify;
    }

    /***
     * Set gmtModify
     * 
     * @param gmtModify gmtModify
     */
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

}

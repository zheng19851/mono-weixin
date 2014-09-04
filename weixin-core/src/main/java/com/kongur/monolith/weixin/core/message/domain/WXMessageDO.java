package com.kongur.monolith.weixin.core.message.domain;

import java.util.Date;

/***
 *
 */
public class WXMessageDO {

    private Long    id;

    private String  appId;

    private String  msgId;

    private String  msgType;

    private String  fromUser;

    private String  toUser;

    private Long    createTime;

    private String  features;

    private Integer status;

    private Date    gmtCreate;

    private Date    gmtModify;

    public WXMessageDO(Message msg) {
        this.appId = msg.getAppId();
        this.msgId = msg.getMsgId();
        this.msgType = msg.getMsgType();
        this.fromUser = msg.getFromUserName();
        this.toUser = msg.getToUserName();
        this.createTime = msg.getCreateTime();
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

    /***
	 * 
	 */
    @Override
    public String toString() {
        StringBuffer mb = new StringBuffer();
        mb.append("id" + id);
        mb.append("appId" + appId);
        mb.append("msgId" + msgId);
        mb.append("msgType" + msgType);
        mb.append("fromUser" + fromUser);
        mb.append("toUser" + toUser);
        mb.append("createTime" + createTime);
        mb.append("gmtCreate" + gmtCreate);
        mb.append("gmtModify" + gmtModify);

        return new StringBuilder().append("MessageDO").append(mb).toString();
    }

}

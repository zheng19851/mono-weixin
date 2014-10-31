package com.runssnail.monolith.weixin.client.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板消息
 * 
 * @author zhengwei
 */
public class TemplateMessage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3461751857004431486L;

    /**
     * 公众号appid
     */
    private String            appId;

    /**
     * 模板id
     */
    private String            templateId;

    private String            url;

    /**
     * 接受方openid
     */
    private String            toUser;

    private String            topColor         = "#FF0000";

    private KeyNote           first;

    private List<KeyNote>     keyNotes         = new ArrayList<KeyNote>();

    private KeyNote           remark;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTopColor() {
        return topColor;
    }

    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public void setFirst(String value, String color) {
        this.first = new KeyNote(value, color);
    }

    public void setRemark(String value, String color) {
        this.remark = new KeyNote(value, color);
    }

    public void addKeyNote(String value, String color) {
        KeyNote keyNote = new KeyNote(value, color);
        keyNotes.add(keyNote);
    }

    public KeyNote getFirst() {
        return first;
    }

    public void setFirst(KeyNote first) {
        this.first = first;
    }

    public List<KeyNote> getKeyNotes() {
        return keyNotes;
    }

    public void setKeyNotes(List<KeyNote> keyNotes) {
        this.keyNotes = keyNotes;
    }

    public KeyNote getRemark() {
        return remark;
    }

    public void setRemark(KeyNote remark) {
        this.remark = remark;
    }

}

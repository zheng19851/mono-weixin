package com.kongur.monolith.weixin.core.domain;

import java.util.Date;
import java.util.List;

import com.kongur.monolith.common.DomainBase;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.common.domain.dto.Reply;
import com.kongur.monolith.weixin.common.domain.enums.EnumReplyType;

/**
 * �������ظ�
 * 
 * @author zhengwei
 * @date 2014-2-20
 */
public class ReplyDO extends DomainBase implements Reply {

    /**
     * 
     */
    private static final long serialVersionUID = -7719757655897015135L;

    private String            id;

    /**
     * �ظ����ͣ���eventType=clickʱ�õ�
     */
    private String            type;

    /**
     * �ظ����ݣ�type=textʱ�õ�
     */
    private String            content;

    /**
     * �ظ���Դid��type=image, voice��ʱ�õ�
     */
    private String            resourceId;

    /**
     * ͼ����Ϣʱ�õ�
     */
    private List<ItemDO>      items;

    /**
     * Ĭ�ϱ����ظ�
     */
    private boolean           active           = false;

    private Date              gmtCreate;

    private Date              gmtModify;

    private String            errors;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    /**
     * ͼ������
     * 
     * @return
     */
    public int getItemsCount() {
        return this.items != null ? this.items.size() : 0;
    }

    /**
     * �Ƿ����ͼ��
     * 
     * @return
     */
    public boolean hasItems() {
        return getItemsCount() > 0;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * �Ƿ������ظ���Ĭ�ϱ���
     * 
     * @return
     */
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ItemDO> getItems() {
        return items;
    }

    public void setItems(List<ItemDO> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public boolean isText() {
        return EnumReplyType.isText(this.type);
    }

    public boolean isImage() {
        return EnumReplyType.isImage(this.type);
    }

    public boolean isVoice() {
        return EnumReplyType.isVoice(this.type);
    }

    public boolean isVedio() {
        return EnumReplyType.isVedio(this.type);
    }

    public boolean isMusic() {
        return EnumReplyType.isMusic(this.type);
    }

    public boolean isNews() {
        return EnumReplyType.isNews(this.type);
    }

    /**
     * �Ƿ���Դ�ظ�����
     * 
     * @return
     */
    public boolean isResource() {
        return isImage() || isVoice() || isVedio() || isMusic();
    }

    public String getTypeDesc() {
        return EnumReplyType.getDescByValue(this.type);
    }

    public boolean hasErrors() {
        if (StringUtil.isNotBlank(this.errors)) {
            return true;
        }

        if (this.items != null) {
            for (ItemDO item : this.items) {
                if (item.hasErrors()) {
                    return true;
                }
            }
        }

        return false;

    }

}

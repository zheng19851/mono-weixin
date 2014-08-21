package com.kongur.monolith.weixin.core.menu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kongur.monolith.common.DomainBase;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.client.menu.EnumMenuType;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;

/**
 * �˵�����
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public class MenuDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 2098017289149628095L;

    /**
     * �˵�ID
     */
    private String            id;

    /**
     * ���˵�ID
     */
    private String            parentId;

    /**
     * �˵�����
     */
    private String            name;

    /**
     * �Ƿ��ܲ˵�
     */
    private boolean           function         = false;

    /**
     * �˵��ȼ�
     */
    private int               level;

    /**
     * �˵�����
     */
    private String            type;

    /**
     * �˵�url ��type=viewʱ�õ�
     */
    private String            url;

    /**
     * �¼�key����type=clickʱ�õ�
     */
    private String            eventKey;

    /**
     * �ظ���ϢID
     */
    private String            replyId;

    private ReplyDO           reply;

    /**
     * �Ӳ˵�
     */
    private List<MenuDO>      subMenus;

    /**
     * ������һ��״̬
     */
    private int               oldStatus        = -1;

    /**
     * �˵�״̬, 1=�մ��� 6=������
     */
    private int               status           = STATUS_CREATE;

    /**
     * �´���
     */
    public static final int   STATUS_CREATE    = 1;

    /**
     * ������
     */
    public static final int   STATUS_PUSHED    = 6;

    private Date              gmtCreate;

    private Date              gmtModify;

    /**
     * �Ƿ�����, 0:û�� 1������
     */
    private int               locked           = 0;

    /**
     * ��ҳ��Ȩ����, snsapi_base �� snsapi_userinfo
     */
    private String            userOAuthType;

    /**
     * ��ѡ��˵�����Ϊ����ʱ���Ƿ���Ҫ��url����oauth2.0��Ȩ
     * 
     * @return
     */
    public boolean isNeedUserOAuth() {
        return StringUtil.isNotBlank(this.userOAuthType);
    }

    public String getUserOAuthType() {
        return userOAuthType;
    }

    public void setUserOAuthType(String userOAuthType) {
        this.userOAuthType = userOAuthType;
    }

    public boolean hasErrors() {
        return this.reply.hasErrors();
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    /**
     * �Ƿ���
     * 
     * @return
     */
    public boolean isLocked() {
        return 1 == this.locked;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.oldStatus = this.status; // �ȱ���
        this.status = status;
        this.lock();
    }

    public void lock() {
        this.locked = 1;
    }

    public void unlock() {
        this.locked = 0;
    }

    /**
     * �ع�״̬
     */
    public void reset() {
        int temp = this.oldStatus;
        this.oldStatus = this.status;

        this.status = temp;

        unlock();
    }

    public ReplyDO getReply() {
        return reply;
    }

    public void setReply(ReplyDO reply) {
        this.reply = reply;
    }

    /**
     * �Ӳ˵�����
     * 
     * @return
     */
    public int subCount() {
        return subMenus != null ? this.subMenus.size() : 0;
    }

    /**
     * view�������͵Ĳ˵�
     * 
     * @return
     */
    public boolean isView() {
        return EnumMenuType.isView(this.type);
    }

    /**
     * �Ƿ�click����
     * 
     * @return
     */
    public boolean isClick() {
        return EnumMenuType.isClick(this.type);
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFunction() {
        return function;
    }

    public void setFunction(boolean function) {
        this.function = function;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MenuDO> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuDO> subMenus) {
        this.subMenus = subMenus;
    }

    public int getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(int oldStatus) {
        this.oldStatus = oldStatus;
    }

    /**
     * ״̬�Ƿ���
     * 
     * @return
     */
    // public boolean hasUpdated() {
    // return this.oldStatus != -1;
    // }

    /**
     * ���ƻ�����Ϣ
     * 
     * @param newMenu
     */
    public void copyFrom(MenuDO newMenu) {

        this.name = newMenu.name;
        this.type = newMenu.type;
        this.function = newMenu.function;
        this.eventKey = newMenu.eventKey;
        this.replyId = newMenu.replyId;
        this.reply = newMenu.reply;
        this.url = newMenu.url;
        this.gmtModify = newMenu.gmtModify;
    }

    /**
     * �Ƿ�һ���˵�
     * 
     * @return
     */
    public boolean isLevelOne() {
        return StringUtil.isBlank(this.parentId);
    }

    public void addSubMenu(MenuDO newMenu) {
        if (this.subMenus == null) {
            this.subMenus = new ArrayList<MenuDO>();
        }

        this.subMenus.add(newMenu);

    }

    public boolean hasSubMenus() {
        return subCount() > 0;
    }

    /**
     * �Ƿ�������
     * 
     * @return
     */
    public boolean isPushed() {
        return STATUS_PUSHED == this.status;
    }

    /**
     * �Ƿ��ı��ظ�����
     * 
     * @return
     */
    public boolean isTextReply() {
        return this.reply != null && this.reply.isText();
    }

    /**
     * ͼ�Ļظ���Ϣ
     * 
     * @return
     */
    public boolean isNewsReply() {
        return this.reply != null && this.reply.isNews();
    }

    /**
     * �Ƿ���Դ�ظ�����
     * 
     * @return
     */
    public boolean isResourceReply() {
        return this.reply != null && this.reply.isResource();
    }

    /**
     * �Ƿ�������˵�
     * 
     * @return
     */
    public boolean hasParent() {
        return !StringUtil.isBlank(this.parentId);
    }

}

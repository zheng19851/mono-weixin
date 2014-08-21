package com.kongur.monolith.weixin.client.domain.dto;

import java.util.List;

import com.kongur.monolith.common.DomainBase;
import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.client.domain.enums.EnumMenuType;

/**
 * �˵������ڲ��ӿڵ����õ�
 * 
 * @author zhengwei
 * @date 2014��2��25��
 */
public class Menu extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -9131049689494154312L;

    /**
     * ����
     */
    private String            name;

    /**
     * ����
     */
    private String            type;

    /**
     * �¼�key
     */
    private String            eventKey;

    /**
     * url
     */
    private String            url;

    /**
     * �Ӳ˵�
     */
    private List<Menu>        subMenus;

    /**
     * �Ƿ��ܲ˵�
     * 
     * @return
     */
    public boolean isFunction() {
        return !StringUtil.isBlank(this.type);
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

    public boolean hasSubMenus() {
        return subCount() > 0;
    }

    /**
     * �Ӳ˵�����
     * 
     * @return
     */
    public int subCount() {
        return subMenus != null ? this.subMenus.size() : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

}

package com.runssnail.monolith.weixin.core.menu.domain;

import java.util.List;

import com.runssnail.monolith.common.DomainBase;

/**
 * ͼ��
 * 
 * @author zhengwei
 * @date 2014��2��20��
 */
public class ItemDO extends DomainBase {

    /**
	 * 
	 */
    private static final long serialVersionUID = -818033585251661290L;

    private String            id;

    /**
     * ͼ����Ϣ����
     */
    private String            title;

    /**
     * ͼ����Ϣ����
     */
    private String            description;

    /**
     * ͼƬ���ӣ�֧��JPG��PNG��ʽ���Ϻõ�Ч��Ϊ��ͼ360*200��Сͼ200*200
     */
    private String            picUrl;

    /**
     * ���ͼ����Ϣ��ת����
     */
    private String            url;

    private List<String>      errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean hasErrors() {
        return this.errors != null && !this.errors.isEmpty();
    }

}

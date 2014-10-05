package com.runssnail.monolith.weixin.client.custom.message;

import com.runssnail.monolith.common.DomainBase;

/**
 * ͼ����Ϣ������
 * 
 * @author zhengwei
 */
public class Article extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 6271804432177291580L;

    /**
     * ����
     */
    private String            title;

    /**
     * ����
     */
    private String            description;

    /**
     * �������ת������
     */
    private String            url;

    /**
     * ͼ����Ϣ��ͼƬ���ӣ�֧��JPG��PNG��ʽ���Ϻõ�Ч��Ϊ��ͼ640*320��Сͼ80*80
     */
    private String            picUrl;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}

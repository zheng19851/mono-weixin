package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * 链接消息特性
 * 
 * @author zhengwei
 */
public class LinkFeatures extends AbstractFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = -5041951828269077567L;

    private String            url;
    private String            title;
    private String            description;

    public LinkFeatures(String url, String title, String description) {
        this.url = url;
        this.title = title;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

}

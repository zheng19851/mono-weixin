package com.kongur.monolith.weixin.client.support;

import java.util.Map;

import com.kongur.monolith.common.DomainBase;

/**
 * 远程事件
 * 
 * @author zhengwei
 */
public class RemoteAppEvent extends DomainBase {

    /**
     * 
     */
    private static final long   serialVersionUID = 2557513313995362643L;

    /**
     * 事件类型
     */
    private EnumAppEventType    appEventType;

    /**
     * 资源id
     */
    private String              resourceId;

    /**
     * 扩展属性
     */
    private Map<String, String> attributes       = null;

    public RemoteAppEvent(EnumAppEventType appEventType) {
        this(appEventType, null);
    }

    public RemoteAppEvent(EnumAppEventType appEventType, String resourceId) {
        this.appEventType = appEventType;
        this.resourceId = resourceId;
    }

    public EnumAppEventType getAppEventType() {
        return appEventType;
    }

    public void setAppEventType(EnumAppEventType appEventType) {
        this.appEventType = appEventType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

}

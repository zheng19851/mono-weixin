package com.kongur.monolith.weixin.core.support.event;

import java.util.EventObject;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ÊÂ¼þ
 * 
 * @author zhengwei
 */
public class AppEvent extends EventObject {

    /**
     * 
     */
    private static final long serialVersionUID = -4585860393952767480L;

    public AppEvent(Object source) {
        super(source);
    }

    public String toString() {

        return ToStringBuilder.reflectionToString(source);
    }

}

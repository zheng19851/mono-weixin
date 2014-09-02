package com.kongur.monolith.weixin.client.support;

/**
 * 事件类型
 * 
 * @author zhengwei
 */
public enum EnumAppEventType {

    REFRESH_DEFAULT_REPLY("REFRESH_DEFAULT_REPLY"),

    REFRESH_SUBSCRIBE_REPLY("REFRESH_SUBSCRIBE_REPLY"),

    REFRESH_PUBLIC_NO("refresh_public_no"),
    
    REFRESH_MENUS("refresh_menus"),
    ;

    private String val;

    private EnumAppEventType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

}

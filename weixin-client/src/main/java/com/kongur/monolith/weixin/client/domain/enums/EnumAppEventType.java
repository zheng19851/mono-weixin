package com.kongur.monolith.weixin.client.domain.enums;

/**
 * �¼�����
 * 
 * @author zhengwei
 */
public enum EnumAppEventType {

    REFRESH_ERROR_REPLY("REFRESH_ERROR_REPLY"),

    REFRESH_SUBSCRIBE_REPLY("REFRESH_SUBSCRIBE_REPLY"),

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

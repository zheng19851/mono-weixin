package com.kongur.monolith.weixin.client.domain.enums;

/**
 * �˵�����
 * 
 * @author zhengwei
 * @date 2014��2��20��
 */
public enum EnumMenuType {

    CLICK("click", "�¼�����"), VIEW("view", "��������")

    ;

    private EnumMenuType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean isView(String val) {
        return VIEW.getValue().equalsIgnoreCase(val);
    }

    public static boolean isClick(String val) {
        return CLICK.getValue().equalsIgnoreCase(val);
    }

}

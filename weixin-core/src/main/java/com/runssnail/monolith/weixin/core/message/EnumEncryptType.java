package com.runssnail.monolith.weixin.core.message;


/**
 * 微信消息加密方式
 * 
 * @author zhengwei
 *
 */
public enum EnumEncryptType {
    AES("aes", "aes加密"), RAW("raw", "不加密");

    private String type;
    private String desc;

    private EnumEncryptType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean isEncryptEnabled(String encryptType) {
        return AES.getType().equalsIgnoreCase(encryptType);
    }

}

package com.runssnail.monolith.weixin.core.message;


/**
 * ΢����Ϣ���ܷ�ʽ
 * 
 * @author zhengwei
 *
 */
public enum EnumEncryptType {
    AES("aes", "aes����"), RAW("raw", "������");

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

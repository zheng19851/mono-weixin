package com.runssnail.monolith.weixin.core.mp.domain;

/**
 * 公众号状态
 * 
 * @author zhengwei
 */
public enum EnumPublicNoStatus {

    ENABLED(1, "已激活"), DISABLED(-1, "已禁用");

    private int    status;
    private String desc;

    private EnumPublicNoStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean isEnabled(Integer status) {
        return status != null && ENABLED.getStatus() == status;
    }

}

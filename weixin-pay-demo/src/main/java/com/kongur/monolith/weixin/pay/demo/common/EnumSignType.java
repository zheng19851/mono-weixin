package com.kongur.monolith.weixin.pay.demo.common;

/**
 * Ç©Ãû·½Ê½
 * 
 * @author zhengwei
 */
public enum EnumSignType {
    SHA1("sha1"), MD5("md5"), RSA("rsa");

    private String signType;

    private EnumSignType(String signType) {
        this.signType = signType;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public boolean isMd5() {
        return MD5 == this;
    }

    public boolean isSHA1() {
        return SHA1 == this;
    }

    public boolean isRsa() {
        return RSA == this;
    }

}

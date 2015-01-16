package com.runssnail.monolith.weixin.trade.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Ç©Ãû·½Ê½
 * 
 * @author zhengwei
 */
public enum EnumSignType {
    SHA1("sha1"), MD5("md5"), RSA("rsa");

    private String                                 val;

    private static final Map<String, EnumSignType> CACHE = new HashMap<String, EnumSignType>(values().length);

    static {
        for (EnumSignType entry : values()) {
            CACHE.put(entry.val, entry);
        }

    }

    private EnumSignType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
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

    public EnumSignType getByVal(String val) {
        return CACHE.get(val.toUpperCase());
    }

    public static boolean isSHA1(String val) {
        return SHA1.getVal().equalsIgnoreCase(val);
    }

}

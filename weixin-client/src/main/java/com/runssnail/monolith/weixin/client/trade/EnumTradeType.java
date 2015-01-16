package com.runssnail.monolith.weixin.client.trade;

import java.util.HashMap;
import java.util.Map;

/**
 * ����������Դ��JSAPI��NATIVE��APP������
 * 
 * @author zhengwei
 */
public enum EnumTradeType {

    JSAPI("JSAPI"), NATIVE("NATIVE"), APP("APP");

    private String                                  val;

    private static final Map<String, EnumTradeType> CACHE = new HashMap<String, EnumTradeType>(values().length);

    static {
        for (EnumTradeType entry : values()) {
            CACHE.put(entry.val, entry);
        }

    }

    private EnumTradeType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public boolean isJsApi() {
        return JSAPI == this;
    }

}

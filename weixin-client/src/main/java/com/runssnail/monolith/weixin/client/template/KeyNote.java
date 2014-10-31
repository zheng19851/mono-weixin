package com.runssnail.monolith.weixin.client.template;

import java.io.Serializable;

public class KeyNote implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4436797659473925588L;

    private String value;

    private String color = "#CCCCCC";

    public KeyNote(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}

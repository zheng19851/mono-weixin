package com.runssnail.monolith.weixin.core.message.domain.features;

/**
 * 地理位置消息
 * 
 * @author zhengwei
 */
public class LocationFeatures extends AbstractFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = -7366621489344129994L;
    private String            locationX;
    private String            locationY;
    private int               scale;
    private String            label;

    public LocationFeatures(String locationX, String locationY, int scale, String label) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

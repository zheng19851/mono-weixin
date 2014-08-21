package com.kongur.monolith.weixin.core.message.domain;

/**
 * �¼���Ϣ����
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public enum EnumEventType {

    SUBSCRIBE("subscribe", "��ע�¼�"),

    UNSUBSCRIBE("unsubscribe", "ȡ����ע�¼�"),

    SCAN("SCAN", "��ά��ɨ��-�û��ѹ�ע�¼�"),

    LOCATION("LOCATION", "�ϱ�����λ���¼�"),

    CLICK("CLICK", "����˵���ȡ��Ϣʱ���¼�"),

    VIEW("VIEW", "����˵���ת����ʱ���¼�"),

    ;

    private EnumEventType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * ��Ϣ���͵�ֵ
     */
    private String value;

    /**
     * ��Ϣ����
     */
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

    /**
     * �Ƿ��ע�¼�
     * 
     * @param evenType
     * @return
     */
    public static boolean isSubscribe(String evenType) {
        return SUBSCRIBE.getValue().equalsIgnoreCase(evenType);
    }

    /**
     * �Ƿ�ȡ����ע�¼�
     * 
     * @param eventType
     * @return
     */
    public static boolean isUnSubscribe(String eventType) {
        return UNSUBSCRIBE.getValue().equalsIgnoreCase(eventType);
    }

    /**
     * �Ƿ��ϱ�����λ���¼�
     * 
     * @param eventType
     * @return
     */
    public static boolean isLocation(String eventType) {
        return LOCATION.getValue().equalsIgnoreCase(eventType);
    }

    /**
     * �Զ���˵��¼�
     * 
     * @param eventType
     * @return
     */
    public static boolean isClick(String eventType) {
        return CLICK.getValue().equalsIgnoreCase(eventType);
    }

    /**
     * ����˵���ת����ʱ���¼�����
     * 
     * @param eventType
     * @return
     */
    public static boolean isView(String eventType) {
        return VIEW.getValue().equalsIgnoreCase(eventType);
    }

    /**
     * ɨ���ά��ʱ���û��ѹ�ע
     * 
     * @param eventType
     * @return
     */
    public static boolean isScan(String eventType) {
        return false;
    }

}

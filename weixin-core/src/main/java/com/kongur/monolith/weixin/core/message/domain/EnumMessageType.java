package com.kongur.monolith.weixin.core.message.domain;

/**
 * ��Ϣ����
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public enum EnumMessageType {

    // ��ͨ��Ϣ
    TEXT("text", "�ı���Ϣ"), IMAGE("image", "ͼƬ��Ϣ"), VOICE("voice", "������Ϣ"), VIDEO("video", "��Ƶ��Ϣ"), LOCATION("location",
                                                                                                           "����λ����Ϣ"),
    LINK("link", "������Ϣ"),

    // �¼�������Ϣ
    EVENT("event", "�¼���Ϣ"),

    ;

    private EnumMessageType(String value, String desc) {
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
     * �Ƿ��ı���Ϣ
     * 
     * @param msgType
     * @return
     */
    public static boolean isText(String msgType) {
        return TEXT.getValue().equalsIgnoreCase(msgType);
    }

    /**
     * �Ƿ��¼���Ϣ
     * 
     * @param msgType
     * @return
     */
    public static boolean isEvent(String msgType) {
        return EVENT.getValue().equalsIgnoreCase(msgType);
    }

    /**
     * ������������Ϣ
     * 
     * @param msgType
     * @return
     */
    public static boolean isVoice(String msgType) {
        return VOICE.getValue().equalsIgnoreCase(msgType);
    }

    /**
     * ͼƬ��Ϣ
     * 
     * @param msgType
     * @return
     */
    public static boolean isImage(String msgType) {
        return IMAGE.getValue().equalsIgnoreCase(msgType);
    }

    /**
     * ��Ƶ��Ϣ
     * 
     * @param msgType
     * @return
     */
    public static boolean isVideo(String msgType) {
        return VIDEO.getValue().equalsIgnoreCase(msgType);
    }

    public static boolean isLocation(String msgType) {
        return LOCATION.getValue().equalsIgnoreCase(msgType);
    }

    public static boolean isLink(String msgType) {
        return LINK.getValue().equalsIgnoreCase(msgType);
    }

}

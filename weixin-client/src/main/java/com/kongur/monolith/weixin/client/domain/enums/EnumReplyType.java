package com.kongur.monolith.weixin.client.domain.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �ظ���Ϣ������������Ϣ����
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public enum EnumReplyType {

    TEXT("text", "�ı���Ϣ"), IMAGE("image", "ͼƬ��Ϣ"), VOICE("voice", "������Ϣ"), VEDIO("vedio", "��Ƶ��Ϣ"),
    MUSIC("music", "������Ϣ"), NEWS("news", "ͼ����Ϣ")

    ;

    private EnumReplyType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String                                  value;
    private String                                  desc;

    private static final Map<String, EnumReplyType> CACHE        = new HashMap<String, EnumReplyType>(values().length);
    // Ŀǰ���õ�����
    private static final List<EnumReplyType>        USABLE_TYPES = new ArrayList<EnumReplyType>(values().length);

    static {
        USABLE_TYPES.add(TEXT);
        // USABLE_TYPES.add(IMAGE);
        // USABLE_TYPES.add(VOICE);
        USABLE_TYPES.add(NEWS); // ͼ��

        for (EnumReplyType entry : values()) {
            CACHE.put(entry.getValue(), entry);
        }

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean isText(String replyType) {
        return TEXT.getValue().equals(replyType);
    }

    public static boolean isImage(String replyType) {
        return IMAGE.getValue().equals(replyType);
    }

    public static boolean isVoice(String replyType) {
        return VOICE.getValue().equals(replyType);
    }

    public static boolean isVedio(String replyType) {
        return VEDIO.getValue().equals(replyType);
    }

    public static boolean isMusic(String replyType) {
        return MUSIC.getValue().equals(replyType);
    }

    public static boolean isNews(String replyType) {
        return NEWS.getValue().equals(replyType);
    }

    /**
     * ��ǰ���õ�����
     * 
     * @return
     */
    public static List<EnumReplyType> getUsableTypes() {
        return USABLE_TYPES;
    }

    public static String getDescByValue(String type) {
        EnumReplyType reply = getByValue(type);

        return reply != null ? reply.getDesc() : null;
    }

    private static EnumReplyType getByValue(String type) {
        return CACHE.get(type);
    }
}

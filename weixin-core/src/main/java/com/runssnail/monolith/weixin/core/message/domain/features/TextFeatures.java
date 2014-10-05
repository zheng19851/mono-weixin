package com.runssnail.monolith.weixin.core.message.domain.features;


/**
 * �ı���Ϣ ���Զ���
 * 
 * @author zhengwei
 */
public class TextFeatures extends AbstractFeatures {

    /**
     * 
     */
    private static final long serialVersionUID = 283214067082655198L;
    private String content;

    public TextFeatures(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        TextFeatures text = new TextFeatures("fffaaa");
        System.out.println(text.buildJson());

    }

}

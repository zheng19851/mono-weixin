package com.kongur.monolith.weixin.core.message.domain;


/**
 * ��������֤��Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public class DeveloperValidateMessage extends AbstractMessage {

    /**
     * 
     */
    private static final long  serialVersionUID                = 6425642897705407895L;

    public static final String DEVELOPER_VALIDATE_MESSAGE_TYPE = "dvm";

    /**
     * ����ַ���
     */
    private String             echostr;

    public DeveloperValidateMessage(String signature, String timestamp, String nonce, String echostr) {
        super(signature, timestamp, nonce);
        this.echostr = echostr;
        super.setMsgType("dvm");
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

}

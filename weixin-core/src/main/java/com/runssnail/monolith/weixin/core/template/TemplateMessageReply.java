package com.runssnail.monolith.weixin.core.template;

import com.runssnail.monolith.weixin.client.template.TemplateMessage;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * ģ����Ϣ�ظ�����
 * 
 * @author zhengwei
 */
public class TemplateMessageReply implements Reply {

    private TemplateMessage msg;

    public TemplateMessageReply(TemplateMessage msg) {
        this.msg = msg;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public boolean isResource() {
        return false;
    }

    public TemplateMessage getMsg() {
        return msg;
    }

    public void setMsg(TemplateMessage msg) {
        this.msg = msg;
    }

}

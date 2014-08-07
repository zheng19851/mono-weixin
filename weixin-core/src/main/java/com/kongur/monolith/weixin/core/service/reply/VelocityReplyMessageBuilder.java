package com.kongur.monolith.weixin.core.service.reply;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.Reply;
import com.kongur.monolith.weixin.core.domain.message.Message;

/**
 * ��Velocityʵ�ֵĻظ���Ϣ������
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public abstract class VelocityReplyMessageBuilder<R extends Reply> extends AbstractReplyMessageBuilder<R> {

    private VelocityEngine velocityEngine;

    /**
     * ģ��·��
     */
    private String         template;

    /**
     * �����ظ�����
     * 
     * @param reply
     * @param msg
     * @param result
     */
    protected void doBuild(R reply, Message msg, Result<String> result) {

        Map model = new HashMap();
        buildDefaultModelParams(reply, msg, model);
        buildModelParams(reply, msg, model);
        String buildResult = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, getTemplate(), model);
        if (log.isDebugEnabled()) {
            log.debug("build reply content successfully, content=" + buildResult);
        }

        result.setResult(buildResult);
    }

    /**
     * ����Ĭ�ϵ�velocity����
     * 
     * @param reply
     * @param msg
     * @param model
     */
    protected void buildDefaultModelParams(R reply, Message msg, Map model) {
        model.put("toUser", msg.getFromUserName());
        model.put("fromUser", msg.getToUserName());

        model.put("createTime", new Date().getTime());

        model.put("reply", reply);

    }

    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * ģ��
     * 
     * @return
     */
    public String getTemplate() {
        return this.template;
    }

    /**
     * ����
     * 
     * @param reply
     * @param msg
     * @return
     */
    protected abstract void buildModelParams(R reply, Message msg, Map model);

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

}

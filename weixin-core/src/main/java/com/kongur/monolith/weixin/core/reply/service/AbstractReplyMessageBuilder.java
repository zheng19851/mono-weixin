package com.kongur.monolith.weixin.core.reply.service;

import org.apache.log4j.Logger;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.core.message.domain.Message;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * ReplyMessageBuilder����ʵ��
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
public abstract class AbstractReplyMessageBuilder<R extends Reply> implements ReplyMessageBuilder<R> {

    protected final Logger log = Logger.getLogger(getClass());

    public AbstractReplyMessageBuilder() {

    }

    @Override
    public Result<String> build(R reply) {
        return this.build(reply, null);
    }

    @Override
    public Result<String> build(R reply, Message msg) {
        Result<String> result = new Result<String>();
        result.setSuccess(true);
        if (log.isDebugEnabled()) {
            log.debug("======build reply message start======");
        }

        preBuild(reply, msg, result);

        if (!result.isSuccess()) {
            return result;
        }

        doBuild(reply, msg, result);

        if (!result.isSuccess()) {
            return result;
        }

        postBuild(reply, msg, result);

        if (log.isDebugEnabled()) {
            log.debug("======build reply message end======");
        }

        return result;
    }

    protected abstract void doBuild(R reply, Message msg, Result<String> result);

    /**
     * ��֤�ظ������Ƿ���ȷ
     * 
     * @param reply
     * @param msg
     * @param result
     */
    protected void validate(R reply, Message msg, Result<String> result) {

    }

    /**
     * ����
     * 
     * @param reply
     * @param msg
     * @param result
     */
    protected void postBuild(R reply, Message msg, Result<String> result) {

    }

    /**
     * Ԥ����
     * 
     * @param reply
     * @param msg
     * @param result
     */
    protected void preBuild(R reply, Message msg, Result<String> result) {
        validate(reply, msg, result);

        if (!result.isSuccess()) {
            return;
        }
    }

}

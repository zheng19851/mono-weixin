package com.runssnail.monolith.weixin.core.reply.builder;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * ReplyMessageBuilder抽象实现
 * 
 * @author zhengwei
 * @date 2014年2月21日
 */
public abstract class AbstractReplyMessageBuilder<R extends Reply> implements ReplyMessageBuilder<R>, Ordered {

    protected final Logger log   = Logger.getLogger(getClass());

    /**
     * 排序字段
     */
    private int            order = LOWEST_PRECEDENCE;

    public AbstractReplyMessageBuilder() {

    }

    @Override
    public int getOrder() {
        return order;
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
     * 验证回复数据是否正确
     * 
     * @param reply
     * @param msg
     * @param result
     */
    protected void validate(R reply, Message msg, Result<String> result) {

    }

    /**
     * 后处理
     * 
     * @param reply
     * @param msg
     * @param result
     */
    protected void postBuild(R reply, Message msg, Result<String> result) {

    }

    /**
     * 预处理
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

    public void setOrder(int order) {
        this.order = order;
    }

}

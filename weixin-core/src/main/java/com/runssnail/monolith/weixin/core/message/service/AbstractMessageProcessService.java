package com.runssnail.monolith.weixin.core.message.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.reply.builder.ReplyMessageBuilder;
import com.runssnail.monolith.weixin.core.reply.builder.ReplyMessageBuilderResolver;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * 消息处理服务
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public abstract class AbstractMessageProcessService<M extends Message> implements MessageProcessService<M> {

    protected final Logger                     log = Logger.getLogger(getClass());

    // @Resource(name = "messageService")
    @Autowired
    private MessageService                     messageService;

    // @Resource(name = "replyMessageBuilderResolver")
    @Autowired
    private ReplyMessageBuilderResolver<Reply> replyMessageBuilderResolver;

    @Override
    public Result<String> process(M msg) {

        Result<String> result = new Result<String>();
        result.setSuccess(true);

        if (log.isDebugEnabled()) {
            log.debug("==========process message start===========, msg=" + msg);
        }

        preProcess(msg, result);

        if (!result.isSuccess()) {
            return result;
        }

        doProcess(msg, result);

        if (!result.isSuccess()) {
            return result;
        }

        postProcess(msg, result);

        if (log.isDebugEnabled()) {
            log.debug("==========process message finished==========");
        }

        return result;
    }

    /**
     * 后处理
     * 
     * @param msg
     * @param reply
     * @param result
     */
    protected void postProcess(M msg, Result<String> result) {
        if (log.isDebugEnabled()) {
            log.debug("post process message start...");
        }

        if (log.isDebugEnabled()) {
            log.debug("post process message end...");
        }
    }

    /**
     * 预处理
     * 
     * @param msg
     * @param result
     */
    protected void preProcess(M msg, Result<String> result) {
        if (log.isDebugEnabled()) {
            log.debug("pre process message start...");
        }

        if (messageService.contains(msg)) {
            log.warn("the message has exists, msg=" + msg);
            result.setError("1001", "the message has exists.");
            return;
        }

        // 内部ID
        String id = messageService.store(msg);

        if (StringUtil.isBlank(id)) {
            log.error("can not store the message., msg=" + msg);
            result.setError("1002", "store message error.");
        }

        if (log.isDebugEnabled()) {
            log.debug("pre process message end...");
        }

    }

    /**
     * 真正处理业务
     * 
     * @param msg
     * @param result
     * @return
     */
    protected void doProcess(M msg, Result<String> result) {

        if (log.isDebugEnabled()) {
            log.debug("do process start...");
        }

        Reply reply = buildReply(msg);

        if (reply != null) {
            if (log.isDebugEnabled()) {
                log.debug("build reply successfully, reply=" + reply);
            }

            ReplyMessageBuilder<Reply> builder = replyMessageBuilderResolver.resolve(reply);

            Result<String> buildResult = builder.build(reply, msg);

            if (!buildResult.isSuccess()) {
                result.setError(buildResult.getResultCode(), buildResult.getResultInfo());
            } else {
                result.setResult(buildResult.getResult());
            }
        } else {
            log.warn("the reply is null, so the service will not build reply message. msg=" + msg);
        }

        if (log.isDebugEnabled()) {
            log.debug("do process end...");
        }
    }

    /**
     * 创建回复对象，默认不创建
     * 
     * @param msg
     * @return
     */
    protected Reply buildReply(M msg) {
        return null;
    }

}

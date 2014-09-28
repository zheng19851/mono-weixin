package com.kongur.monolith.weixin.core.reply.builder;

import java.util.List;

import org.apache.log4j.Logger;

import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * 默认的 回复消息处理服务 查找服务
 * 
 * @author zhengwei
 * @date 2014年2月21日
 */
// @Service("replyMessageBuilderResolver")
public class DefaultReplyMessageBuilderResolver implements ReplyMessageBuilderResolver<Reply> {

    private final Logger                     log = Logger.getLogger(getClass());

    private List<ReplyMessageBuilder<Reply>> replyMessageBuilders;

    /**
     * 默认 的创建器
     */
    // @Resource(name = "discardReplyMessageBuilder")
    private ReplyMessageBuilder<Reply>       defaultReplyMessageBuilder;

    @Override
    public ReplyMessageBuilder<Reply> resolve(Reply reply) {

        for (ReplyMessageBuilder<Reply> builder : replyMessageBuilders) {
            if (builder.supports(reply)) {
                if (log.isDebugEnabled()) {
                    log.debug("find ReplyMessageBuilder, name=" + builder.getClass().getSimpleName());
                }
                return builder;
            }
        }

        log.warn("can not find a ReplyMessageBuilder, so will use the default ReplyMessageBuilder="
                 + this.defaultReplyMessageBuilder.getClass().getSimpleName());

        return this.defaultReplyMessageBuilder;
    }

    public List<ReplyMessageBuilder<Reply>> getReplyMessageBuilders() {
        return replyMessageBuilders;
    }

    public void setReplyMessageBuilders(List<ReplyMessageBuilder<Reply>> replyMessageBuilders) {
        this.replyMessageBuilders = replyMessageBuilders;
    }

    public ReplyMessageBuilder<Reply> getDefaultReplyMessageBuilder() {
        return defaultReplyMessageBuilder;
    }

    public void setDefaultReplyMessageBuilder(ReplyMessageBuilder<Reply> defaultReplyMessageBuilder) {
        this.defaultReplyMessageBuilder = defaultReplyMessageBuilder;
    }

}

package com.runssnail.monolith.weixin.core.reply.builder;

import java.util.List;

import org.apache.log4j.Logger;

import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * Ĭ�ϵ� �ظ���Ϣ������� ���ҷ���
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
// @Service("replyMessageBuilderResolver")
public class DefaultReplyMessageBuilderResolver implements ReplyMessageBuilderResolver<Reply> {

    private final Logger                     log = Logger.getLogger(getClass());

    private List<ReplyMessageBuilder> replyMessageBuilders;

    /**
     * Ĭ�� �Ĵ�����
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

    public List<ReplyMessageBuilder> getReplyMessageBuilders() {
        return replyMessageBuilders;
    }

    public void setReplyMessageBuilders(List<ReplyMessageBuilder> replyMessageBuilders) {
        this.replyMessageBuilders = replyMessageBuilders;
    }

    public ReplyMessageBuilder<Reply> getDefaultReplyMessageBuilder() {
        return defaultReplyMessageBuilder;
    }

    public void setDefaultReplyMessageBuilder(ReplyMessageBuilder<Reply> defaultReplyMessageBuilder) {
        this.defaultReplyMessageBuilder = defaultReplyMessageBuilder;
    }

}

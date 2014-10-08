package com.runssnail.monolith.weixin.core.reply.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * ReplyMessageBuilderResolver创建工厂
 * 
 * @author zhengwei
 */
public class ReplyMessageBuilderResolverFactoryBean implements FactoryBean<ReplyMessageBuilderResolver<Reply>>, InitializingBean, ApplicationContextAware {

    private final Logger                       log                           = Logger.getLogger(getClass());

    private ReplyMessageBuilderResolver<Reply> replyMessageBuilderResolver;

    private List<ReplyMessageBuilder>          replyMessageBuilders;

    /**
     * 默认 的创建器
     */
    private ReplyMessageBuilder<Reply>         defaultReplyMessageBuilder;

    private ApplicationContext                 applicationContext;

    /**
     * 是否自动收集
     */
    private boolean                            detectAllReplyMessageBuilders = true;

    @Override
    public ReplyMessageBuilderResolver<Reply> getObject() throws Exception {
        return this.replyMessageBuilderResolver;
    }

    @Override
    public Class<?> getObjectType() {
        return ReplyMessageBuilderResolver.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Assert.notNull(this.defaultReplyMessageBuilder, "the default ReplyMessageBuilder can not be null.");

        if (this.detectAllReplyMessageBuilders) {

            // 自动收集
            Map<String, ReplyMessageBuilder> matchingBeans = this.applicationContext.getBeansOfType(ReplyMessageBuilder.class);

            this.replyMessageBuilders = new ArrayList<ReplyMessageBuilder>(matchingBeans.values());

        }

        if (this.replyMessageBuilders != null && !this.replyMessageBuilders.isEmpty()) {
            AnnotationAwareOrderComparator.sort(this.replyMessageBuilders);
        } else {
            log.warn("there are 0 count of ReplyMessageBuilders.");
        }

        DefaultReplyMessageBuilderResolver replyMessageBuilderResolver = new DefaultReplyMessageBuilderResolver();
        replyMessageBuilderResolver.setDefaultReplyMessageBuilder(this.defaultReplyMessageBuilder);
        replyMessageBuilderResolver.setReplyMessageBuilders(this.replyMessageBuilders);
        this.replyMessageBuilderResolver = replyMessageBuilderResolver;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
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

    public boolean isDetectAllReplyMessageBuilders() {
        return detectAllReplyMessageBuilders;
    }

    public void setDetectAllReplyMessageBuilders(boolean detectAllReplyMessageBuilders) {
        this.detectAllReplyMessageBuilders = detectAllReplyMessageBuilders;
    }

}

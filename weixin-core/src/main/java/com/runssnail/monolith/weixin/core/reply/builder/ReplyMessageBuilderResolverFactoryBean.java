package com.runssnail.monolith.weixin.core.reply.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * ReplyMessageBuilderResolver创建工厂
 * 
 * @author zhengwei
 */
public class ReplyMessageBuilderResolverFactoryBean implements FactoryBean<ReplyMessageBuilderResolver<Reply>>, InitializingBean, ApplicationContextAware {

    private final Logger                       log         = Logger.getLogger(getClass());

    private ReplyMessageBuilderResolver<Reply> replyMessageBuilderResolver;

    private List<ReplyMessageBuilder<Reply>>   replyMessageBuilders;

    /**
     * 默认 的创建器
     */
    private ReplyMessageBuilder<Reply>         defaultReplyMessageBuilder;

    private ApplicationContext                 applicationContext;

    /**
     * 是否自动收集
     */
    private boolean                            autoCollect = false;

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
        Assert.notNull(this.defaultReplyMessageBuilder, "the default ReplyMessageBuilder can not be null.");

        if (this.autoCollect) {

            // 自动收集
            Map<String, ReplyMessageBuilder> builderMap = this.applicationContext.getBeansOfType(ReplyMessageBuilder.class);

            if (!builderMap.isEmpty()) {

                List<ReplyMessageBuilder<Reply>> builders = new ArrayList<ReplyMessageBuilder<Reply>>(builderMap.size());

                for (Entry<String, ReplyMessageBuilder> entry : builderMap.entrySet()) {
                    ReplyMessageBuilder builder = entry.getValue();
                    if (builder != this.defaultReplyMessageBuilder) {
                        builders.add(entry.getValue());
                    }
                }

                this.replyMessageBuilders = builders;

                if (log.isInfoEnabled()) {
                    log.info("auto-find " + builderMap.size() + " count of ReplyMessageBuilders.");
                }

            } else {

                log.warn("can not auto-find any ReplyMessageBuilders.");
            }
        }

        if (this.replyMessageBuilders != null) {
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

    public boolean isAutoCollect() {
        return autoCollect;
    }

    public void setAutoCollect(boolean autoCollect) {
        this.autoCollect = autoCollect;
    }

}

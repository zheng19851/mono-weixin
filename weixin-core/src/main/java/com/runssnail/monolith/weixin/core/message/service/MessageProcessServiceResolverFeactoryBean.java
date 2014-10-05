package com.runssnail.monolith.weixin.core.message.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

import com.runssnail.monolith.weixin.core.message.domain.Message;

/**
 * MessageProcessServiceResolver创建工厂
 * 
 * @author zhengwei
 */
public class MessageProcessServiceResolverFeactoryBean implements FactoryBean<MessageProcessServiceResolver>, InitializingBean {

    private final Logger                         log = Logger.getLogger(getClass());

    private MessageProcessServiceResolver        messageProcessServiceResolver;

    private List<MessageProcessService<Message>> messageProcessServices;

    /**
     * 默认处理器
     */
    private MessageProcessService<Message>       defaultMessageProcessService;

    @Override
    public MessageProcessServiceResolver getObject() throws Exception {
        return messageProcessServiceResolver;
    }

    @Override
    public Class<?> getObjectType() {
        return MessageProcessServiceResolver.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notEmpty(this.messageProcessServices);
        Assert.notNull(this.defaultMessageProcessService);

        // 排序，需要用@Order
        AnnotationAwareOrderComparator.sort(this.messageProcessServices);

        DefaultMessageProcessServiceResolver messageProcessServiceResolver = new DefaultMessageProcessServiceResolver();
        messageProcessServiceResolver.setMessageProcessServices(this.messageProcessServices);

        messageProcessServiceResolver.setDefaultMessageProcessService(this.defaultMessageProcessService);

        this.messageProcessServiceResolver = messageProcessServiceResolver;
        if (log.isInfoEnabled()) {
            log.info("init MessageProcessServiceResolver, messageProcessServices=" + messageProcessServices);
        }

    }

    public List<MessageProcessService<Message>> getMessageProcessServices() {
        return messageProcessServices;
    }

    public void setMessageProcessServices(List<MessageProcessService<Message>> messageProcessServices) {
        this.messageProcessServices = messageProcessServices;
    }

    public MessageProcessService<Message> getDefaultMessageProcessService() {
        return defaultMessageProcessService;
    }

    public void setDefaultMessageProcessService(MessageProcessService<Message> defaultMessageProcessService) {
        this.defaultMessageProcessService = defaultMessageProcessService;
    }

}

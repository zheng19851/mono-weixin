package com.runssnail.monolith.weixin.core.message.service;

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
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.runssnail.monolith.weixin.core.message.domain.Message;

/**
 * MessageProcessServiceResolver创建工厂
 * 
 * @author zhengwei
 */
@Service("messageProcessServiceResolver")
public class MessageProcessServiceResolverFeactoryBean implements FactoryBean<MessageProcessServiceResolver>, InitializingBean, ApplicationContextAware {

    private final Logger                  log                             = Logger.getLogger(getClass());

    private MessageProcessServiceResolver messageProcessServiceResolver;

    private List<MessageProcessService>   messageProcessServices;

    /**
     * 默认处理器
     */
    private MessageProcessService         defaultMessageProcessService;

    /**
     * true时，收集所有MessageProcessService
     */
    private boolean                       detectAllMessageProcessServices = true;

    private ApplicationContext            applicationContext;

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
        // Assert.notEmpty(this.messageProcessServices);
        // Assert.notNull(this.defaultMessageProcessService);

        if (this.detectAllMessageProcessServices) {
            // 自动收集
            Map<String, MessageProcessService> matchingBeans = this.applicationContext.getBeansOfType(MessageProcessService.class);

            this.messageProcessServices = new ArrayList<MessageProcessService>(matchingBeans.values());
        }

        Assert.notEmpty(this.messageProcessServices, "messageProcessServices can not be empty.");

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

    public List<MessageProcessService> getMessageProcessServices() {
        return messageProcessServices;
    }

    public void setMessageProcessServices(List<MessageProcessService> messageProcessServices) {
        this.messageProcessServices = messageProcessServices;
    }

    public MessageProcessService<Message> getDefaultMessageProcessService() {
        return defaultMessageProcessService;
    }

    public void setDefaultMessageProcessService(MessageProcessService<Message> defaultMessageProcessService) {
        this.defaultMessageProcessService = defaultMessageProcessService;
    }

    public boolean isDetectAllMessageProcessServices() {
        return detectAllMessageProcessServices;
    }

    public void setDetectAllMessageProcessServices(boolean detectAllMessageProcessServices) {
        this.detectAllMessageProcessServices = detectAllMessageProcessServices;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}

package com.kongur.monolith.weixin.core.message.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

import com.kongur.monolith.weixin.core.message.domain.Message;

/**
 * ΢�� ��Ϣ������񴴽�����
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
// @Service("defaultMessageProcessServiceResolver")
public class DefaultMessageProcessServiceResolver implements MessageProcessServiceResolver {

    private final Logger                         log = Logger.getLogger(getClass());

    private List<MessageProcessService<Message>> messageProcessServices;

    /**
     * Ĭ�ϵ�MessageProcessService���Ҳ���ƥ���serviceʱ����Ĭ�������
     */
    // @Resource(name = "discardMessageProcessService")
    private MessageProcessService<Message>       defaultMessageProcessService;

    // @PostConstruct
    public void init() {
        if (this.messageProcessServices == null) {
            this.messageProcessServices = new ArrayList<MessageProcessService<Message>>();
        }

        Assert.notNull(this.defaultMessageProcessService, "the default MessageProcessService can not be null.");

        AnnotationAwareOrderComparator.sort(this.messageProcessServices);

        if (log.isInfoEnabled()) {
            log.info("init MessageProcessServiceResolver, messageProcessServices=" + messageProcessServices);
        }
    }

    @Override
    public MessageProcessService<Message> resolve(Message msg) {

        for (MessageProcessService<Message> messageProcessService : this.messageProcessServices) {
            if (messageProcessService.supports(msg)) {
                if (log.isInfoEnabled()) {
                    log.info("find MessageProcessService, name=" + messageProcessService.getClass().getSimpleName());
                }
                return messageProcessService;
            }
        }

        log.warn("can not find a supported MessageProcessService, so will use the default MessageProcessService="
                 + this.defaultMessageProcessService.getClass().getSimpleName());

        return this.defaultMessageProcessService;
    }

    @Override
    public boolean addMessageProcessService(MessageProcessService<Message> service) {
        return this.messageProcessServices.add(service);
    }

    @Override
    public boolean removeMessageProcessService(MessageProcessService<Message> service) {
        return this.messageProcessServices.remove(service);
    }

    public MessageProcessService<Message> getDefaultMessageProcessService() {
        return defaultMessageProcessService;
    }

    public void setDefaultMessageProcessService(MessageProcessService<Message> defaultMessageProcessService) {
        this.defaultMessageProcessService = defaultMessageProcessService;
    }

    public void setMessageProcessServices(List<MessageProcessService<Message>> messageProcessServices) {
        this.messageProcessServices = messageProcessServices;
    }

    public List<MessageProcessService<Message>> getMessageProcessServices() {
        return messageProcessServices;
    }

}

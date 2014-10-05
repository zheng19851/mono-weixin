package com.runssnail.monolith.weixin.core.message.service;

import org.apache.log4j.Logger;

/**
 * ≥ÈœÛ µœ÷
 * 
 * @author zhengwei
 */
public abstract class AbstractMessageStore implements MessageStore {

    protected final Logger log = Logger.getLogger(getClass());

    private String         name;

    public AbstractMessageStore(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}

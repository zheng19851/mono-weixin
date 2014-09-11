package com.kongur.monolith.weixin.core.message.domain.features;

import java.io.StringWriter;

import org.apache.log4j.Logger;

import com.kongur.monolith.weixin.core.common.JacksonObjectMapper;

/**
 * Features ����ʵ��
 * 
 * @author zhengwei
 */
public abstract class AbstractFeatures implements Features {

    /**
     * 
     */
    private static final long serialVersionUID = -1321453923421950364L;
    protected transient final Logger log = Logger.getLogger(getClass());

    @Override
    public String buildJson() {
        StringWriter out = new StringWriter();
        JacksonObjectMapper.writeValue(out, this);
        return out.toString();
    }
}

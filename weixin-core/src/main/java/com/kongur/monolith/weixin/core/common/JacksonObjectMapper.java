package com.kongur.monolith.weixin.core.common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * jackson ObjectMapper Ϊ�̰߳�ȫ��������ֻҪһ����������
 * 
 * @author zhengwei
 */
public abstract class JacksonObjectMapper {

    private static final Logger       log      = Logger.getLogger(JacksonObjectMapper.class);

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }

    public static JsonNode readTree(String json) throws JsonProcessingException, IOException {
        return INSTANCE.readTree(json);
    }

    public static void writeValue(OutputStream out, Object value) {
        try {
            INSTANCE.writeValue(out, value);
        } catch (Exception e) {
            log.error("write object to OutputStream error", e);
        }
    }

    public static void writeValue(Writer out, Object value) {
        try {
            INSTANCE.writeValue(out, value);
        } catch (Exception e) {
            log.error("write object to OutputStream error", e);
        }
    }
}

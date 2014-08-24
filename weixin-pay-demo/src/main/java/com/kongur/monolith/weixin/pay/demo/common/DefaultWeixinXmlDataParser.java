package com.kongur.monolith.weixin.pay.demo.common;

import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.thoughtworks.xstream.XStream;

/**
 * @author zhengwei
 */
@Service("weixinXmlDataParser")
public class DefaultWeixinXmlDataParser implements WeixinXmlDataParser {

    private XStream xStream;

    public void init() {
        if (this.xStream == null) {
            this.xStream = new XStream();
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Result<T> parse(String xmlData, Class<T> clazz) {
        Result<T> result = new Result<T>();
        this.xStream.alias("xml", clazz);

        T obj = (T) this.xStream.fromXML(xmlData);
        
        result.setResult(obj);
        result.setSuccess(true);
        return result;
    }

}

package com.kongur.monolith.weixin.pay.demo.common;

import com.kongur.monolith.common.result.Result;

/**
 * 微信xml数据解析器
 * 
 * @author zhengwei
 */
public interface WeixinXmlDataParser {

    /**
     * 将xml数据解析成对象
     * 
     * @param xmlData xml数据
     * @param clazz 对象的class类型
     * @return
     */
    <T> Result<T> parse(String xmlData, Class<T> clazz);

}

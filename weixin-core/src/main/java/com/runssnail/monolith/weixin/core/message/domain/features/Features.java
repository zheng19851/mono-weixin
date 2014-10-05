package com.runssnail.monolith.weixin.core.message.domain.features;

import java.io.Serializable;

/**
 * 消息特性
 * 
 * @author zhengwei
 */
public interface Features extends Serializable {

    /**
     * 生成json字符串
     * 
     * @return
     */
    String buildJson();
}

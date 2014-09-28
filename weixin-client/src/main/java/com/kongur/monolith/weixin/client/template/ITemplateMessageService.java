package com.kongur.monolith.weixin.client.template;

import com.kongur.monolith.common.result.Result;

/**
 * 模板消息服务
 * 
 * @author zhengwei
 */
public interface ITemplateMessageService {

    /**
     * 发送模板消息
     * @param msg
     * @return 返回msgid
     */
    Result<String> send(TemplateMessage msg);

}

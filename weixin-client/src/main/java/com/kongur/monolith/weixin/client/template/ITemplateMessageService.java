package com.kongur.monolith.weixin.client.template;

import com.kongur.monolith.common.result.Result;

/**
 * ģ����Ϣ����
 * 
 * @author zhengwei
 */
public interface ITemplateMessageService {

    /**
     * ����ģ����Ϣ
     * @param msg
     * @return ����msgid
     */
    Result<String> send(TemplateMessage msg);

}

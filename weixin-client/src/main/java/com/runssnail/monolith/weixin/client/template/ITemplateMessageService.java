package com.runssnail.monolith.weixin.client.template;

import com.runssnail.monolith.common.result.Result;

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

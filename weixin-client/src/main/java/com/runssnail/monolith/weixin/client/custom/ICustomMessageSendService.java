package com.runssnail.monolith.weixin.client.custom;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.custom.message.CustomMessage;

/**
 * ���ĵ��û�������Ϣ
 * 
 * @author zhengwei
 */
public interface ICustomMessageSendService {

    /**
     * ����
     * 
     * @param msg
     * @return
     */
    Result<Object> send(CustomMessage msg);

}

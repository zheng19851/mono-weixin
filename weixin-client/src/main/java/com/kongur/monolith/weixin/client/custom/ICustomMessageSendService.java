package com.kongur.monolith.weixin.client.custom;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.custom.message.CustomMessage;

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

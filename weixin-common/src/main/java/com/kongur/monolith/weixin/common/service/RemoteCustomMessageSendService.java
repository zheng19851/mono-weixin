package com.kongur.monolith.weixin.common.service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.custom.CustomMessage;

/**
 * ���ĵ��û�������Ϣ
 * 
 * @author zhengwei
 */
public interface RemoteCustomMessageSendService {

    /**
     * ����
     * 
     * @param msg
     * @return
     */
    Result<Object> send(CustomMessage msg);

}

package com.kongur.monolith.weixin.core.custom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.common.domain.dto.custom.CustomMessage;
import com.kongur.monolith.weixin.common.service.RemoteCustomMessageSendService;
import com.kongur.monolith.weixin.core.base.service.WeixinApiService;

/**
 * 默认的RemoteCustomMessageSendService实现
 * 
 * @author zhengwei
 *
 */
@Service("customMessageSendService")
public class DefaultCustomMessageSendService implements RemoteCustomMessageSendService {
    
    private String apiUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=${access_token}";
    
    @Autowired
    private WeixinApiService weixinApiService;

    @Override
    public Result<Object> send(CustomMessage msg) {
        Result<Object> result = new Result<Object>();

        String postParams = null;
        weixinApiService.doPost(apiUrl, postParams);
        
        return result;
    }

}

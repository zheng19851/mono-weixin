package com.kongur.monolith.weixin.core.custom;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.custom.ICustomMessageSendService;
import com.kongur.monolith.weixin.client.custom.message.CustomMessage;
import com.kongur.monolith.weixin.core.base.service.WeixinApiService;
import com.kongur.monolith.weixin.core.reply.builder.ReplyMessageBuilder;
import com.kongur.monolith.weixin.core.reply.builder.ReplyMessageBuilderResolver;
import com.kongur.monolith.weixin.core.reply.domain.Reply;

/**
 * Ĭ�ϵ�RemoteCustomMessageSendServiceʵ��
 * 
 * @author zhengwei
 */
@Service("customMessageSendService")
public class DefaultCustomMessageSendService implements ICustomMessageSendService {

    private String                             apiUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=${access_token}";

    @Autowired
    private WeixinApiService                   weixinApiService;

    @Autowired
    private ReplyMessageBuilderResolver<Reply> replyMessageBuilderResolver;

    @Override
    public Result<Object> send(CustomMessage msg) {
        Result<Object> result = new Result<Object>();

        CustomReply<CustomMessage> custemReply = new CustomReply<CustomMessage>(msg);
        ReplyMessageBuilder<Reply> builder = replyMessageBuilderResolver.resolve(custemReply);

        Result<String> buildResult = builder.build(custemReply);
        if (!buildResult.isSuccess()) {
            result.setError(buildResult.getResultCode(), buildResult.getResultInfo());
            return result;
        }

        Result<JSONObject> apiResult = weixinApiService.doPost(msg.getAppId(), apiUrl, buildResult.getResult());
        if (!apiResult.isSuccess()) {
            result.setError(apiResult.getResultCode(), apiResult.getResultInfo());
            return result;
        }

        result.setSuccess(true);
        return result;
    }

}

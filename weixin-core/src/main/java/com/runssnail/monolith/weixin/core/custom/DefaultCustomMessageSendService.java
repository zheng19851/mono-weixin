package com.runssnail.monolith.weixin.core.custom;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.custom.ICustomMessageSendService;
import com.runssnail.monolith.weixin.client.custom.message.CustomMessage;
import com.runssnail.monolith.weixin.core.base.service.WeixinApiService;
import com.runssnail.monolith.weixin.core.reply.builder.ReplyMessageBuilder;
import com.runssnail.monolith.weixin.core.reply.builder.ReplyMessageBuilderResolver;
import com.runssnail.monolith.weixin.core.reply.domain.Reply;

/**
 * 默认的RemoteCustomMessageSendService实现
 * 
 * @author zhengwei
 */
@Service("customMessageSendService")
public class DefaultCustomMessageSendService implements ICustomMessageSendService {

    private String                             apiUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=${access_token}";

    @Resource(name = "weixinApiService")
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

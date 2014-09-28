package com.kongur.monolith.weixin.core.template;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kongur.monolith.common.result.Result;
import com.kongur.monolith.weixin.client.template.ITemplateMessageService;
import com.kongur.monolith.weixin.client.template.TemplateMessage;
import com.kongur.monolith.weixin.core.base.service.WeixinApiService;
import com.kongur.monolith.weixin.core.reply.builder.ReplyMessageBuilder;
import com.kongur.monolith.weixin.core.reply.builder.ReplyMessageBuilderResolver;

/**
 * 默认的模板消息服务
 * 
 * @author zhengwei
 */
@Service("templateMessageService")
public class DefaultTemplateMessageService implements ITemplateMessageService {

    @Autowired
    private WeixinApiService                                  weixinApiService;

    @Autowired
    private ReplyMessageBuilderResolver<TemplateMessageReply> replyMessageBuilderResolver;

    @Value("${weixin.api.template.url:https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=$!{access_token}}")
    private String                                            apiUrl;

    @Override
    public Result<String> send(TemplateMessage msg) {
        Result<String> result = new Result<String>();

        TemplateMessageReply reply = new TemplateMessageReply(msg);

        ReplyMessageBuilder<TemplateMessageReply> builder = replyMessageBuilderResolver.resolve(reply);

        Result<String> buildResult = builder.build(reply);
        if (!buildResult.isSuccess()) {
            result.setError(buildResult.getResultCode(), buildResult.getResultInfo());
            return result;
        }

        Result<JSONObject> apiResult = weixinApiService.doPost(msg.getAppId(), apiUrl, buildResult.getResult());
        if (!apiResult.isSuccess()) {
            result.setError(apiResult.getResultCode(), apiResult.getResultInfo());
            return result;
        }

        String msgId = apiResult.getResult().getString("msgid");
        result.setSuccess(true);
        result.setResult(msgId);
        return result;
    }

}

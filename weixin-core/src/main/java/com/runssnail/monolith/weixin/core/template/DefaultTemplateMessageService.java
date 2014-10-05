package com.runssnail.monolith.weixin.core.template;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.client.template.ITemplateMessageService;
import com.runssnail.monolith.weixin.client.template.TemplateMessage;
import com.runssnail.monolith.weixin.core.base.service.WeixinApiService;
import com.runssnail.monolith.weixin.core.reply.builder.ReplyMessageBuilder;
import com.runssnail.monolith.weixin.core.reply.builder.ReplyMessageBuilderResolver;

/**
 * 默认的模板消息服务
 * 
 * @author zhengwei
 */
@Service("templateMessageService")
public class DefaultTemplateMessageService implements ITemplateMessageService {

    @Resource(name = "weixinApiService")
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

package com.runssnail.monolith.weixin.core.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

public class UrlEncodeTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String snsapiUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state=123#wechat_redirect";

        String url = "http://172.21.2.103:8080/weixin/mechanic/index.htm";

        try {
            url = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Ìæ»»rediect_urlºÍscope
        url = MessageFormat.format(snsapiUrl, "wx8f27cba60d24a19d", url, "snsapi_base");

        System.out.println(url);
    }

}

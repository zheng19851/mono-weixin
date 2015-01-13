package com.runssnail.monolith.weixin.core.test;

import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

    public static void main(String[] args) throws Exception {

        HttpClient httpClient = new DefaultHttpClient();

        String content = "小开心怎么样了。。。";
        content  =  URLEncoder.encode(content, "GBK");
        System.out.println("content=" + content);
        String internalApiUrl = "http://202.91.244.252/qd/SMSSendYD?usr=6322&pwd=gtan_6322g&mobile=15257390290&sms=" + content;
//        internalApiUrl = URLEncoder.encode(internalApiUrl, "GBK");
        System.out.println(internalApiUrl);
        HttpGet httpGet = new HttpGet(internalApiUrl);
        
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();

        String retData = EntityUtils.toString(entity, "UTF-8");
        EntityUtils.consume(entity); // Consume response content
        
        httpClient.getConnectionManager().shutdown();
        
        System.out.println("retData=" + retData);//141210095448268
    }

}

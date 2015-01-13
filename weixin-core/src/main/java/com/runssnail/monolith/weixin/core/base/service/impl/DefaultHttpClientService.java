package com.runssnail.monolith.weixin.core.base.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.runssnail.monolith.common.result.Result;
import com.runssnail.monolith.weixin.core.base.service.HttpClientService;
import com.runssnail.monolith.weixin.core.base.service.HttpException;
import com.runssnail.monolith.weixin.core.support.WeixinApiHelper;

/**
 * ΢��API�ӿڷ���
 * 
 * @author zhengwei
 * @date 2014-2-17
 */
// @Service("httpClientService")
public class DefaultHttpClientService implements HttpClientService {

    private HttpClient   httpClient     = new DefaultHttpClient();

    private String       defaultCharset = "UTF-8";

    private final Logger log            = Logger.getLogger(getClass());

    public void close() {
        if (this.httpClient != null) {
            httpClient.getConnectionManager().shutdown(); // �ر�����,�ͷ���Դ
        }
    }

    public Result<String> executeGet(String apiUrl) throws HttpException {

        return this.executeGet(apiUrl, null);
    }

    /**
     * ִ��get����
     * 
     * @param apiUrl api url
     * @param getParams �������������GET URL
     * @return
     */
    public Result<String> executeGet(String apiUrl, Map<String, String> getParams) throws HttpException {

        if (log.isDebugEnabled()) {
            log.debug("invoke executeGet, apiUrl=" + apiUrl + ", getParams=" + getParams);
        }

        final Result<String> result = new Result<String>();

        String retData = null;

        String internalApiUrl = apiUrl;

        if (getParams != null && !getParams.isEmpty()) {
            StringBuilder sb = new StringBuilder(internalApiUrl);
            for (Entry<String, String> entry : getParams.entrySet()) {
                try {
                    sb.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),
                                                                                               this.defaultCharset));
                } catch (UnsupportedEncodingException e) {
                    throw new HttpException("encode error, content=" + entry.getValue() + ", charset="
                                           + this.defaultCharset, e);
                }
            }
        }

        HttpGet httpGet = new HttpGet(internalApiUrl);
        try {

            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                log.error("request error, There is no response data. apiUrl=" + internalApiUrl);
                result.setError("9001", "There is no response data");
                return result;
            }

            retData = EntityUtils.toString(entity, this.defaultCharset);
            EntityUtils.consume(entity); // Consume response content
            if (log.isInfoEnabled()) {
                log.info("get response=" + retData);
            }

        } catch (Exception e) {
            log.error("http get error, apiUrl=" + internalApiUrl, e);
            throw new HttpException("http get error", e);
        }

        if (log.isDebugEnabled()) {
            log.debug("invoke executeGet successfully");
        }

        result.setSuccess(true);
        result.setResult(retData);
        return result;
    }

    public Result<String> executePost(String apiUrl, String postParams) throws HttpException {

        if (log.isDebugEnabled()) {
            log.debug("invoke executePost, apiUrl=" + apiUrl + ", postParams=" + postParams);
        }

        final Result<String> result = new Result<String>();

        String internalApiUrl = apiUrl;

        String retData = null;

        HttpPost httpPost = new HttpPost(internalApiUrl);
        httpPost.setEntity(new StringEntity(postParams, "UTF-8"));

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                log.error("request error, There is no response data. apiUrl=" + internalApiUrl);
                result.setError("9001", "There is no response data");
                return result;
            }

            retData = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity); // Consume response content
            if (log.isInfoEnabled()) {
                log.info("post response=" + retData);
            }

        } catch (Exception e) {
            log.error("http post error, apiUrl=" + internalApiUrl, e);
            throw new HttpException("http post error", e);
        }

        if (log.isDebugEnabled()) {
            log.debug("invoke executePost successfully");
        }

        result.setSuccess(true);
        result.setResult(retData);

        return result;
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl) throws HttpException {
        return this.doGet(apiUrl, null);
    }

    @Override
    public Result<JSONObject> doGet(String apiUrl, Map<String, String> getParams) throws HttpException {

        final Result<JSONObject> result = new Result<JSONObject>();

        Result<String> dataResult = executeGet(apiUrl, getParams);

        if (!dataResult.isSuccess()) {
            result.setError(dataResult.getResultCode(), dataResult.getResultInfo());
            return result;
        }

        JSONObject jsonObj = JSONObject.fromObject(dataResult.getResult());
        result.setResult(jsonObj);

        if (!WeixinApiHelper.isSuccess(jsonObj)) {
            result.setError(WeixinApiHelper.getErrCode(jsonObj), WeixinApiHelper.getErrMsg(jsonObj));
            return result;
        }

        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<JSONObject> doPost(String apiUrl, String postParams) throws HttpException {

        if (log.isDebugEnabled()) {
            log.debug("do post data, apiUrl=" + apiUrl + ", postParams=" + postParams);
        }

        final Result<JSONObject> result = new Result<JSONObject>();

        Result<String> dataResult = executePost(apiUrl, postParams);

        if (!dataResult.isSuccess()) {
            log.error("do post data error, apiUrl=" + apiUrl + ", postParams=" + postParams + ", errcode="
                      + dataResult.getResultCode() + ", errmsg=" + dataResult.getResultInfo());
            result.setError(dataResult.getResultCode(), dataResult.getResultInfo());
            return result;
        }

        JSONObject jsonObj = JSONObject.fromObject(dataResult.getResult());
        result.setResult(jsonObj);

        if (!WeixinApiHelper.isSuccess(jsonObj)) {

            String errCode = WeixinApiHelper.getErrCode(jsonObj);
            String errMsg = WeixinApiHelper.getErrMsg(jsonObj);
            log.error("do post data error, apiUrl=" + apiUrl + ", postParams=" + postParams + ", errcode=" + errCode
                      + ", errmsg=" + errMsg);
            result.setError(errCode, errMsg);
            return result;
        }

        if (log.isDebugEnabled()) {
            log.debug("do post data success, apiUrl=" + apiUrl + ", postParams=" + postParams + ", result="
                      + dataResult.getResult());
        }

        result.setSuccess(true);
        return result;

    }

    @Override
    public void init() {

    }

}

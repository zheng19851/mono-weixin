package com.runssnail.monolith.weixin.core.base.service;

import java.util.Map;

import net.sf.json.JSONObject;

import com.runssnail.monolith.common.result.Result;

/**
 * weixin api service
 *
 * @author zhengwei
 * @date 2014��2��26��
 */
public interface WeixinApiService {

    /**
     * ����get����Ĭ�ϼ���access_token�����ؽ��ΪJSONObject
     * 
     * @param apiUrl
     * @return
     * @throws ApiException
     */
    Result<JSONObject> doGet(String apiUrl) throws ApiException;

    /**
     * ����get���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl
     * @return
     * @throws ApiException
     */
    Result<JSONObject> doGet(String apiUrl, boolean replaceAccessToken) throws ApiException;

    /**
     * ����get���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl ���� api URL
     * @param getParams get�������
     * @return
     */
    Result<JSONObject> doGet(String apiUrl, Map<String, String> getParams, boolean replaceAccessToken)
                                                                                                     throws ApiException;

    /**
     * ����post���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl ���� api URL
     * @param postParams post����
     * @return
     */
    Result<JSONObject> doPost(String apiUrl, String postParams, boolean replaceAccessToken) throws ApiException;

    /**
     * ����post����Ĭ�ϻ����access_token�����ؽ��ΪJSONObject
     * 
     * @param apiUrl ���� api URL
     * @param postParams post����
     * @return
     */
    Result<JSONObject> doPost(String apiUrl, String postParams) throws ApiException;

    /**
     * ����post����Ĭ�ϻ����access_token�����ؽ��ΪJSONObject
     * 
     * @param appId ΢��appid
     * @param apiUrl ���� api URL
     * @param postParams post����
     * @return
     */
    Result<JSONObject> doPost(String appId, String apiUrl, String postParams) throws ApiException;

    /**
     * ����get����Ĭ�ϼ���access_token�����ؽ��ΪJSONObject
     * 
     * @param appId
     * @param apiUrl
     * @param getParams
     * @param appendAccessToken
     * @return
     */
    Result<JSONObject> doGet(String appId, String apiUrl, Map<String, String> getParams, boolean replaceAccessToken);

    /**
     * ����get����Ĭ�ϼ���access_token�����ؽ��ΪJSONObject
     * 
     * @param appId
     * @param apiUrl
     * @return
     */
    Result<JSONObject> doGet(String appId, String apiUrl);

    Result<JSONObject> doPost(String appId, String apiUrl, String postParams, boolean replaceAccessToken);

}

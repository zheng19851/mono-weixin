package com.runssnail.monolith.weixin.core.base.service;

import java.util.Map;

import net.sf.json.JSONObject;

import com.runssnail.monolith.common.result.Result;

/**
 * ƽ̨API����
 * 
 * @author zhengwei
 * @date 2014-2-17
 */
public interface HttpClientService  extends LifeCycle {

    /**
     * ����get����
     * 
     * @param apiUrl ���� api URL
     * @return
     */
    Result<String> executeGet(String apiUrl) throws HttpException;

    /**
     * ����get����
     * 
     * @param apiUrl ���� api URL
     * @param getParams get�������
     * @return
     */
    Result<String> executeGet(String apiUrl, Map<String, String> getParams) throws HttpException;

    /**
     * ����post����
     * 
     * @param apiUrl ���� api URL
     * @param postParams post����
     * @return
     */
    Result<String> executePost(String apiUrl, String postParams) throws HttpException;

    /**
     * ����get���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl
     * @return
     * @throws HttpException
     */
    Result<JSONObject> doGet(String apiUrl) throws HttpException;

    /**
     * ����get���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl ���� api URL
     * @param getParams get�������
     * @return
     */
    Result<JSONObject> doGet(String apiUrl, Map<String, String> getParams) throws HttpException;

    /**
     * ����post���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl ���� api URL
     * @param postParams post����
     * @return
     */
    Result<JSONObject> doPost(String apiUrl, String postParams) throws HttpException;

}

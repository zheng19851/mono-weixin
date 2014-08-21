package com.kongur.monolith.weixin.core.base.service;

import java.util.Map;

import net.sf.json.JSONObject;

import com.kongur.monolith.common.result.Result;

/**
 * ƽ̨API����
 * 
 * @author zhengwei
 * @date 2014-2-17
 */
public interface ApiService {

    /**
     * ����get����
     * 
     * @param apiUrl ���� api URL
     * @return
     */
    Result<String> executeGet(String apiUrl) throws ApiException;

    /**
     * ����get����
     * 
     * @param apiUrl ���� api URL
     * @param getParams get�������
     * @return
     */
    Result<String> executeGet(String apiUrl, Map<String, String> getParams) throws ApiException;

    /**
     * ����post����
     * 
     * @param apiUrl ���� api URL
     * @param postParams post����
     * @return
     */
    Result<String> executePost(String apiUrl, String postParams) throws ApiException;

    /**
     * ����get���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl
     * @return
     * @throws ApiException
     */
    Result<JSONObject> doGet(String apiUrl) throws ApiException;

    /**
     * ����get���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl ���� api URL
     * @param getParams get�������
     * @return
     */
    Result<JSONObject> doGet(String apiUrl, Map<String, String> getParams) throws ApiException;

    /**
     * ����post���󣬷��ؽ��ΪJSONObject
     * 
     * @param apiUrl ���� api URL
     * @param postParams post����
     * @return
     */
    Result<JSONObject> doPost(String apiUrl, String postParams) throws ApiException;

}

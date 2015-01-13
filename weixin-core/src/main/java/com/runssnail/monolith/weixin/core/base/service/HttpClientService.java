package com.runssnail.monolith.weixin.core.base.service;

import java.util.Map;

import net.sf.json.JSONObject;

import com.runssnail.monolith.common.result.Result;

/**
 * 平台API服务
 * 
 * @author zhengwei
 * @date 2014-2-17
 */
public interface HttpClientService  extends LifeCycle {

    /**
     * 发起get请求
     * 
     * @param apiUrl 请求 api URL
     * @return
     */
    Result<String> executeGet(String apiUrl) throws HttpException;

    /**
     * 发起get请求
     * 
     * @param apiUrl 请求 api URL
     * @param getParams get请求参数
     * @return
     */
    Result<String> executeGet(String apiUrl, Map<String, String> getParams) throws HttpException;

    /**
     * 发起post请求
     * 
     * @param apiUrl 请求 api URL
     * @param postParams post参数
     * @return
     */
    Result<String> executePost(String apiUrl, String postParams) throws HttpException;

    /**
     * 发起get请求，返回结果为JSONObject
     * 
     * @param apiUrl
     * @return
     * @throws HttpException
     */
    Result<JSONObject> doGet(String apiUrl) throws HttpException;

    /**
     * 发起get请求，返回结果为JSONObject
     * 
     * @param apiUrl 请求 api URL
     * @param getParams get请求参数
     * @return
     */
    Result<JSONObject> doGet(String apiUrl, Map<String, String> getParams) throws HttpException;

    /**
     * 发起post请求，返回结果为JSONObject
     * 
     * @param apiUrl 请求 api URL
     * @param postParams post参数
     * @return
     */
    Result<JSONObject> doPost(String apiUrl, String postParams) throws HttpException;

}

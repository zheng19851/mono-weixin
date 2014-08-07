package com.kongur.monolith.weixin.core.utils;

import net.sf.json.JSONObject;

import com.kongur.monolith.weixin.core.domain.message.WeixinApiErrorCode;

/**
 * ΢��api������
 * 
 * @author zhengwei
 * @date 2014-2-18
 */
public class WeixinApiHelper {

    /**
     * �ж��Ƿ�AccessToken����
     * 
     * @param result
     * @return
     */
    public static boolean isAccessTokenInvalid(JSONObject jsonObj) {

        // û�д����ǵ�Ȼ��AccessToken����
        if (jsonObj == null || isSuccess(jsonObj)) {
            return false;
        }

        String errcode = jsonObj.getString("errcode");

        return WeixinApiErrorCode.isAccessTokenError(errcode);

    }

    /**
     * @param jsonObj
     * @return
     */
    public static boolean isSuccess(JSONObject jsonObj) {

        if (!jsonObj.containsKey("errcode")) {
            return true;
        }

        String errCode = jsonObj.getString("errcode");

        return "0".equals(errCode);
    }

    /**
     * {"errcode":40018,"errmsg":"invalid button name size"}
     * 
     * @param jsonObj
     * @return
     */
    public static String getErrMsg(JSONObject jsonObj) {
        return jsonObj.getString("errmsg");
    }

    /**
     * {"errcode":40018,"errmsg":"invalid button name size"}
     * 
     * @param jsonObj
     * @return
     */
    public static String getErrCode(JSONObject jsonObj) {
        return jsonObj.getString("errcode");
    }

    /**
     * �Ƿ����access_token
     * 
     * @param jsonObj
     * @return
     */
    public static boolean containsAccessToken(JSONObject jsonObj) {
        return jsonObj.containsKey("access_token");
    }

    public static String getAccessToken(JSONObject jsonObj) {
        return jsonObj.getString("access_token");
    }

}

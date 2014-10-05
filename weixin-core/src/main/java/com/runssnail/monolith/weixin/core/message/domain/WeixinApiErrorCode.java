package com.runssnail.monolith.weixin.core.message.domain;

/**
 * ΢��ƽ̨api������Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-18
 */
public enum WeixinApiErrorCode {

    ACCESS_TOKEN_ERROR("40001", "��ȡaccess_tokenʱAppSecret���󣬻���access_token��Ч ")

    ;

    private String errorCode;

    private String errorInfo;

    private WeixinApiErrorCode(String errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * �Ƿ�AccessToken����
     * 
     * @param errcode
     * @return
     */
    public static boolean isAccessTokenError(String errcode) {
        return ACCESS_TOKEN_ERROR.getErrorCode().equalsIgnoreCase(errcode);
    }

}

package com.runssnail.monolith.weixin.core.message.crypto;

@SuppressWarnings("serial")
public class AesException extends Exception {

	public final static int OK = 0;
	public final static int ValidateSignatureError = -40001;
	public final static int ParseXmlError = -40002;
	public final static int ComputeSignatureError = -40003;
	public final static int IllegalAesKey = -40004;
	public final static int ValidateAppidError = -40005;
	public final static int EncryptAESError = -40006;
	public final static int DecryptAESError = -40007;
	public final static int IllegalBuffer = -40008;
	//public final static int EncodeBase64Error = -40009;
	//public final static int DecodeBase64Error = -40010;
	//public final static int GenReturnXmlError = -40011;

	private int code;

	private static String getMessage(int code) {
		switch (code) {
		case ValidateSignatureError:
			return "校验签名失败";
		case ParseXmlError:
			return "解析xml失败";
		case ComputeSignatureError:
			return "sha计算签名失败";
		case IllegalAesKey:
			return "不合法的AESKey";
		case ValidateAppidError:
			return "校验AppID失败";
		case EncryptAESError:
			return "AES加密失败";
		case DecryptAESError:
			return "AES解密失败";
		case IllegalBuffer:
			return "公众平台发送的xml不合法";
//		case EncodeBase64Error:
//			return "base64鍔犲瘑閿欒";
//		case DecodeBase64Error:
//			return "base64瑙ｅ瘑閿欒";
//		case GenReturnXmlError:
//			return "xml鐢熸垚澶辫触";
		default:
			return null; // cannot be
		}
	}

	public int getCode() {
		return code;
	}

	AesException(int code, Exception e) {
		super(getMessage(code), e);
		this.code = code;
	}
	
	AesException(int code) {
        super(getMessage(code));
        this.code = code;
    }

}

package com.runssnail.monolith.weixin.core.message.service;

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
			return "У��ǩ��ʧ��";
		case ParseXmlError:
			return "����xmlʧ��";
		case ComputeSignatureError:
			return "sha����ǩ��ʧ��";
		case IllegalAesKey:
			return "���Ϸ���AESKey";
		case ValidateAppidError:
			return "У��AppIDʧ��";
		case EncryptAESError:
			return "AES����ʧ��";
		case DecryptAESError:
			return "AES����ʧ��";
		case IllegalBuffer:
			return "����ƽ̨���͵�xml���Ϸ�";
//		case EncodeBase64Error:
//			return "base64加密错误";
//		case DecodeBase64Error:
//			return "base64解密错误";
//		case GenReturnXmlError:
//			return "xml生成失败";
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

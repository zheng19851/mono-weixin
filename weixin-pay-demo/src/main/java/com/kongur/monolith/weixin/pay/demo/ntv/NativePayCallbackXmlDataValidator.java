package com.kongur.monolith.weixin.pay.demo.ntv;

/**
 * Native��ԭ����֧���ص��̻���̨post������֤
 * 
 * @author zhengwei
 */
public interface NativePayCallbackXmlDataValidator {

    /**
     * ��֤
     * 
     * @param xmlData
     * @return
     */
    boolean validate(NativePayCallbackXmlDataDO xmlData);

}
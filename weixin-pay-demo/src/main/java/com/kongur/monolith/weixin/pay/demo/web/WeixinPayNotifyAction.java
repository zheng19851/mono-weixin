package com.kongur.monolith.weixin.pay.demo.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.pay.demo.common.WeixinPaymentHelper;

/**
 * ΢��֧��֪ͨ�ӿ�
 * 
 * @author zhengwei
 */
@Controller
public class WeixinPayNotifyAction {

    private final Logger        log = Logger.getLogger(getClass());

    @Autowired
    private WeixinPaymentHelper weixinPaymentHelper;
    
    private static final String FAIL = "fail";
    
    private static final String SUCCESS = "success";

    /**
     * ΢��֧��֪ͨ�ӿ�
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("cgi/weixin/receive_pay_notify.htm")
    public String notifyReceived(HttpServletRequest req) {
        if (log.isDebugEnabled()) {
            log.debug("receive pay notify, params=" + req.getParameterMap());
        }

        // ǩ����ʽsign_type ��String(8) ǩ�����ͣ�ȡֵ��MD5��RSA��Ĭ
        // �ϣ�MD5
        String signType = req.getParameter("sign_type");
        // ǩ��sign ��String(32) ǩ��
        String sign = req.getParameter("sign");

        // ��������У��Ĳ���
        SortedMap<String, String> packageParams = buildPackageParams(req);
        
        // У������ǩ��
        String packageSign = weixinPaymentHelper.buildPackageSign(packageParams);
        if (!sign.equals(packageSign)) {
            log.error("receive pay notify error, the sign is not equals. outSign=" + sign + ", build sign="
                      + packageSign);
            return FAIL;
        }

        // post data
        String postData = readPostData(req);
        if (StringUtil.isBlank(postData)) {
            return FAIL;
        }

        // У��post data ? ��ʱ��У��Ҳ�ǿ��Ե�

        // �޸Ķ���״̬Ϊ����ɹ�
        
        // �޸Ķ���״̬�ɹ�����ϵͳ�����¼���

        return SUCCESS;
    }

    private String readPostData(HttpServletRequest req) {
        String xmlDataStr = null;

        // �ַ���input_charset ��String(8) �ַ�����,ȡֵ��GBK��UTF-8��Ĭ
        // �ϣ�GBK��
        String input_charset = req.getParameter("input_charset");

        InputStream in = null;
        try {
            in = req.getInputStream();
            int len = req.getContentLength();
            byte[] xmlData = new byte[len];
            in.read(xmlData);
            xmlDataStr = new String(xmlData, StringUtil.isBlank(input_charset) ? "GBK" : input_charset);
        } catch (IOException e) {
            log.error("read weixin notify post data error, reqParams=" + req.getParameterMap(), e);
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return xmlDataStr;
    }

    private SortedMap<String, String> buildPackageParams(HttpServletRequest req) {

        String input_charset = req.getParameter("input_charset");

        // ҵ�����
        // ����ģʽtrade_mode ��Int 1-��ʱ����
        // ��������
        String trade_mode = req.getParameter("trade_mode");

        // ����״̬trade_state ��Int ֧�������
        // 0���ɹ�
        // ��������
        String trade_state = req.getParameter("trade_state");
        // ΢�Ź��ں�֧���ӿ��ĵ�V2.7
        // �̻���partner ��String(10) �̻��ţ�Ҳ��֮ǰ�����partnerid,
        // ��΢��ͳһ�����10 λ������
        // (120XXXXXXX)��
        String partner = req.getParameter("partner");
        // ��������bank_type ��String(16) �������ͣ���΢����ʹ��WX
        String bank_type = req.getParameter("bank_type");
        // ���ж�����bank_billno ��String(32) ���ж�����
        String bank_billno = req.getParameter("bank_billno");
        // �ܽ��total_fee ��Int ֧������λΪ�֣����discount
        // ��ֵ��֪ͨ��total_fee + discount =
        // �����total_fee
        String total_fee = req.getParameter("total_fee");
        // ����fee_type ��Int �ֽ�֧������,Ŀǰֻ֧�������,
        // Ĭ��ֵ��1-�����
        String fee_type = req.getParameter("fee_type");
        // ֪ͨID notify_id ��String(128) ֧�����֪ͨid������ĳЩ�ض���
        // ����ֻ����֪ͨid��Ҫ���̻��ݴ�
        // ��ѯ���׽��
        String notify_id = req.getParameter("notify_id");
        // ������transaction_id ��String(28) ���׺ţ�28 λ������ֵ������ǰ10
        // λΪ�̻��ţ�֮��8 λΪ��������
        // �����ڣ���20090415�����10 λ
        // ����ˮ�š�
        String transaction_id = req.getParameter("transaction_id");
        // �̻�������out_trade_no ��String(32) �̻�ϵͳ�Ķ����ţ�������һ�¡�
        String out_trade_no = req.getParameter("out_trade_no");
        // �̻����ݰ�attach ��String(127) �̻����ݰ���ԭ�����أ��ղ�����
        // ����
        String attach = req.getParameter("attach");
        // ֧�����ʱ��time_end ��String(14) ֧�����ʱ�䣬 ��ʽΪ
        // yyyyMMddhhmmss����2009 ��12
        // ��27 ��9 ��10 ��10 ���ʾΪ
        // 20091227091010��ʱ��ΪGMT+8
        // beijing��
        String time_end = req.getParameter("time_end");
        // ��������transport_fee ��Int �������ã���λ�֣�Ĭ��0�����
        // ��ֵ�� ���뱣֤transport_fee +
        // product_fee = total_fee
        String transport_fee = req.getParameter("transport_fee");
        // ��Ʒ����product_fee ��Int ��Ʒ���ã���λ�֡������ֵ����
        // �뱣֤transport_fee +
        // product_fee=total_fee
        String product_fee = req.getParameter("product_fee");
        // �ۿۼ۸�discount ��Int �ۿۼ۸񣬵�λ�֣������ֵ��ͨ
        // ֪��total_fee + discount = �����
        // total_fee
        String discount = req.getParameter("discount");

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        if (StringUtil.isNotBlank(input_charset)) {
            packageParams.put("input_charset", input_charset);

        }
        packageParams.put("trade_mode", trade_mode);
        packageParams.put("trade_state", trade_state);
        packageParams.put("partner", partner);
        packageParams.put("bank_type", bank_type);
        if (StringUtil.isNotBlank(bank_billno)) {
            packageParams.put("bank_billno", bank_billno);

        }
        packageParams.put("total_fee", total_fee);
        packageParams.put("fee_type", fee_type);
        packageParams.put("notify_id", notify_id);
        packageParams.put("transaction_id", transaction_id);
        packageParams.put("out_trade_no", out_trade_no);
        if (StringUtil.isNotBlank(attach)) {

            packageParams.put("attach", attach);
        }
        packageParams.put("time_end", time_end);

        if (StringUtil.isNotBlank(transport_fee)) {
            packageParams.put("transport_fee", transport_fee);
        }

        if (StringUtil.isNotBlank(product_fee)) {
            packageParams.put("product_fee", product_fee);
        }

        if (StringUtil.isNotBlank(discount)) {
            packageParams.put("discount", discount);
        }
        return packageParams;
    }

}

package com.kongur.monolith.weixin.pay.demo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * ΢��֧��֪ͨ�ӿ�
 * 
 * @author zhengwei
 *
 */
@Controller
public class WeixinPayNotifyAction {

    /**
     * ΢��֧��֪ͨ�ӿ�
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("cgi/weixin/receive_pay_notify.htm")
    public String notifyReceived(HttpServletRequest req) {
       
//        ǩ����ʽsign_type ��String(8) ǩ�����ͣ�ȡֵ��MD5��RSA��Ĭ
//        �ϣ�MD5
//        �ַ���input_charset ��String(8) �ַ�����,ȡֵ��GBK��UTF-8��Ĭ
//        �ϣ�GBK��
//        ǩ��sign ��String(32) ǩ��
//        ҵ�����
//        ����ģʽtrade_mode ��Int 1-��ʱ����
//        ��������
//        ����״̬trade_state ��Int ֧�������
//        0���ɹ�
//        ��������
//        ΢�Ź��ں�֧���ӿ��ĵ�V2.7
//        �̻���partner ��String(10) �̻��ţ�Ҳ��֮ǰ�����partnerid,
//        ��΢��ͳһ�����10 λ������
//        (120XXXXXXX)��
//        ��������bank_type ��String(16) �������ͣ���΢����ʹ��WX
//        ���ж�����bank_billno ��String(32) ���ж�����
//        �ܽ��total_fee ��Int ֧������λΪ�֣����discount
//        ��ֵ��֪ͨ��total_fee + discount =
//        �����total_fee
//        ����fee_type ��Int �ֽ�֧������,Ŀǰֻ֧�������,
//        Ĭ��ֵ��1-�����
//        ֪ͨID notify_id ��String(128) ֧�����֪ͨid������ĳЩ�ض���
//        ����ֻ����֪ͨid��Ҫ���̻��ݴ�
//        ��ѯ���׽��
//        ������transaction_id ��String(28) ���׺ţ�28 λ������ֵ������ǰ10
//        λΪ�̻��ţ�֮��8 λΪ��������
//        �����ڣ���20090415�����10 λ
//        ����ˮ�š�
//        �̻�������out_trade_no ��String(32) �̻�ϵͳ�Ķ����ţ�������һ�¡�
//        �̻����ݰ�attach ��String(127) �̻����ݰ���ԭ�����أ��ղ�����
//        ����
//        ֧�����ʱ��time_end ��String(14) ֧�����ʱ�䣬 ��ʽΪ
//        yyyyMMddhhmmss����2009 ��12
//        ��27 ��9 ��10 ��10 ���ʾΪ
//        20091227091010��ʱ��ΪGMT+8
//        beijing��
//        ��������transport_fee ��Int �������ã���λ�֣�Ĭ��0�����
//        ��ֵ�� ���뱣֤transport_fee +
//        product_fee = total_fee
//        ��Ʒ����product_fee ��Int ��Ʒ���ã���λ�֡������ֵ����
//        �뱣֤transport_fee +
//        product_fee=total_fee
//        �ۿۼ۸�discount ��Int �ۿۼ۸񣬵�λ�֣������ֵ��ͨ
//        ֪��total_fee + discount = �����
//        total_fee
        
        // ��������
        
        // У������ǩ��
        
        // ֱ�ӷ��سɹ����������¼�

         // �����¼�ȥ������״̬
        
        
        return "success";
    }
    
}

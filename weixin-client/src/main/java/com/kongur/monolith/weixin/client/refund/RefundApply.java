package com.kongur.monolith.weixin.client.refund;

import com.kongur.monolith.common.DomainBase;

/**
 * �˿��������ݶ���
 * 
 * @author zhengwei
 *
 */
public class RefundApply extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -8949545311970066822L;

//    �̻��� partner
//    �� String(10) �̻���,�ɲƸ�ͨͳһ�����10 λ������
//    (120XXXXXXX)��
//    �̻������� out_trade_no
//    �� String(32) �̻�ϵͳ�ڲ��Ķ�����, out_trade_no ��
//    transaction_id ����һ�����ͬʱ����ʱ
//    transaction_id ����
//    �Ƹ�ͨ������ transaction_id
//    �� String(28) �Ƹ�ͨ���׺�, out_trade_no ��
//    transaction_id ����һ�����ͬʱ����ʱ
//transaction_id ����
//�̻��˿�� out_refund_no
//�� String(32) �̻��˿�ţ�32 ���ַ��ڡ��ɰ�����ĸ,ȷ
//�����̻�ϵͳΨһ��ͬ���˿�Ŷ������
//�Ƹ�ͨ��һ��������ֻ����һ�ο�����
//���˿�ɹ��������ԭ�˿�����·���
//��������ظ��˿
//�ܽ�� total_fee �� Int �����ܽ���λΪ��
//�˿��� refund_fee �� Int �˿��ܽ��,��λΪ��,�����������˿�
//����Ա op_user_id �� Int ����Ա�ʺ�,Ĭ��Ϊ�̻���
//����Ա���� op_user_passwd �� String(32) ����Ա����,Ĭ��Ϊ�̻���̨��¼����
//version Ϊ1.0 ʱ������Ϊ����
//version Ϊ1.1 ʱ������ΪMD5(����)ֵ
//�������ʺ� recv_user_id �� String(64) ת���˿�����˿�ĲƸ�ͨ�ʺš�
//һ��������д��ֻ��������ʧ�ܣ��ʽ�ת����
//�����ֽ��˺�ʱ����״̬Ϊת���������ѯ��
//�ص�refund_status ��7 ��11������дԭ�˿�
//���Ų���д���ֶΣ��ʽ�Ż��˵�ָ���Ƹ�ͨ
//�˺š�����������ֶκ���
//���������� reccv_user_name �� String(32) ת���˿�����˿������(��������˿�Ĳ�
//��ͨ�ʺŰ󶨵�����һ��)
//ͨ���̻�����
//���˿�
//use_spbill_no_fl
//ag
//�� Int ��ͨ���ӿ�
//(https://www.tenpay.com/cgi-bin/v1.0/pay
//_gate.cgi) ֧�����̻����������˿��ȡֵ
//Ϊ1����ͨ�����ĵ�֧���ӿڵģ������贫ֵ��
//�˿����� refund_type �� Int Ϊ�ջ�����1:�̻�������˿2���ֽ��ʺ�
//�˿ 3:�����̻����˿���̻������㣬
//�����ֽ��ʺ��˿ʹ��2 ��3 ʱ������ϵ��
//��ͨ��ͨ�˹��ܡ�
}

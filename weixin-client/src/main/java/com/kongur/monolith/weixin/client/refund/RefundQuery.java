package com.kongur.monolith.weixin.client.refund;

import com.kongur.monolith.common.DomainBase;

/**
 * �˿��ѯ
 * 
 * @author zhengwei
 *
 */
public class RefundQuery extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -7111886599383865633L;

    // �̻��� partner
    // �� String(10) �̻���,�ɲƸ�ͨͳһ�����10 λ������
    // (120XXXXXXX)��
    // �̻������� out_trade_no
    // �� String(32) �̻�ϵͳ�ڲ��Ķ�����, out_trade_no ��
    // transaction_id��out_refund_no��refund_id
    // ����һ�����ͬʱ����ʱ�����ȼ���Ϊ׼��
    // 15
    // ���ȼ�Ϊ��
    // refund_id>out_refund_no>transaction_id>
    // out_trade_no
    // �Ƹ�ͨ������ transaction_id
    // �� String(28) �Ƹ�ͨ������, out_trade_no ��
    // transaction_id��out_refund_no��refund_id
    // ����һ�����ͬʱ����ʱ�����ȼ���Ϊ׼��
    // ���ȼ�Ϊ��
    // refund_id>out_refund_no>transaction_id>
    // out_trade_no
    // �̻��˿�� out_refund_no
    // �� String(32) �̻��˿��, out_trade_no ��
    // transaction_id��out_refund_no��refund_id
    // ����һ�����ͬʱ����ʱ�����ȼ���Ϊ׼��
    // ���ȼ�Ϊ��
    // refund_id>out_refund_no>transaction_id>
    // out_trade_no
    // �Ƹ�ͨ�˿
    // ��
    // refund_id
    // �� String(28) �Ƹ�ͨ�˿��, out_trade_no ��
    // transaction_id��out_refund_no��refund_id
    // ����һ�����ͬʱ����ʱ�����ȼ���Ϊ׼��
    // ���ȼ�Ϊ��
    // refund_id>out_refund_no>transaction_id>
    // out_trade_no
    // ͨ���̻�����
    // ���˿��ѯ
    // use_spbill_no_fl
    // ag
    // �� Int ��ͨ���ӿ�
    // (https://www.tenpay.com/cgi-bin/v1.0/pay
    // _gate.cgi) ֧�����̻����������˿��ȡֵ
    // Ϊ1����ͨ�����ĵ�֧���ӿڵģ������贫ֵ
}

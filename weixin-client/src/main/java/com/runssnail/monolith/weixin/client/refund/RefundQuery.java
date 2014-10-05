package com.runssnail.monolith.weixin.client.refund;

import com.runssnail.monolith.common.DomainBase;

/**
 * �˿��ѯ
 * 
 * @author zhengwei
 */
public class RefundQuery extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -7111886599383865633L;

    /**
     * �̻������� out_trade_no �� String(32) �̻�ϵͳ�ڲ��Ķ�����, out_trade_no �� transaction_id��out_refund_no��refund_id
     * ����һ�����ͬʱ����ʱ�����ȼ���Ϊ׼�� ���ȼ�Ϊ�� refund_id>out_refund_no>transaction_id> out_trade_no
     */
    private String            outTradeNo;

    /**
     * �Ƹ�ͨ������ transaction_id �� String(28) �Ƹ�ͨ������
     */
    private String            transactionId;

    /**
     * �̻��˿�� out_refund_no �� String(32) �̻��˿��
     */
    private String            outRefundNo;

    /**
     * �Ƹ�ͨ�˿�� refund_id �� String(28) �Ƹ�ͨ�˿��
     */
    private String            refundId;

    /**
     * ͨ���̻����� ���˿� use_spbill_no_fl ag �� Int ��ͨ���ӿ� (https://www.tenpay.com/cgi-bin/v1.0/pay _gate.cgi) ֧�����̻����������˿��ȡֵ
     * Ϊ1����ͨ�����ĵ�֧���ӿڵģ������贫ֵ��
     */
    private Integer           useSpbillNoFl;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Integer getUseSpbillNoFl() {
        return useSpbillNoFl;
    }

    public void setUseSpbillNoFl(Integer useSpbillNoFl) {
        this.useSpbillNoFl = useSpbillNoFl;
    }

}

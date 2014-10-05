package com.runssnail.monolith.weixin.client.refund;

import com.runssnail.monolith.common.DomainBase;

/**
 * �˿���
 * 
 * @author zhengwei
 */
public class RefundInfo extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -4128306298346612361L;

    /**
     * �̻��� partner �� String(10) �̻���
     */
    private String            partner;

    /**
     * �Ƹ�ͨ������ transaction_id �� String(28) �Ƹ�ͨ���׺ţ�28 λ������ֵ������ǰ10 λ Ϊ�̻��ţ�֮��8 λΪ�������������ڣ��� 20090415�����10 λ����ˮ�š�
     */
    private String            transactionId;

    /**
     * �̻������� out_trade_no �� String(32) �̻�ϵͳ�ڲ��Ķ�����
     */
    private String            outTradeNo;

    /**
     * �̻��˿�� out_refund_no �� String(32) �̻��˿��
     */
    private String            outRefundNo;

    /**
     * �Ƹ�ͨ�˿�� refund_id�� String(28)
     */
    private String            refundId;

    /**
     * �˿����� refund_channel �� Int �˿�����,0:�˵��Ƹ�ͨ��1:�˵�����
     */
    private Integer           refundChannel;

    /**
     * �˿��� refund_fee �� Int �˿��ܽ��,��λΪ��,�����������˿�
     */
    private Integer           refundFee;

    /**
     * �˿�״̬ refund_status �� Int �˿�״̬�� 4��10���˿�ɹ��� 3��5��6���˿�ʧ�ܡ� 8��9��11���˿���С� 1��2��δȷ������Ҫ�̻�ԭ�˿�����·��� 7��ת��������˿���з����û��Ŀ����ϻ�
     * �߶����ˣ�����ԭ·�˿����п�ʧ�ܣ��ʽ�� �����̻����ֽ��ʺţ���Ҫ�̻��˹���Ԥ��ͨ �����»��߲Ƹ�ͨת�˵ķ�ʽ�����˿
     */
    private Integer           refundStatus;

    /**
     * �������ʺ� recv_user_id �� String(64) ת���˿�����˿�ĲƸ�ͨ�ʺ�
     */
    private String            recvUserId;

    /**
     * ���������� reccv_user_name �� String(32) ת���˿�����˿������(��������˿�Ĳ� ��ͨ�ʺŰ󶨵�����һ��)
     */
    private String            reccvUserName;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
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

    public Integer getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(Integer refundChannel) {
        this.refundChannel = refundChannel;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRecvUserId() {
        return recvUserId;
    }

    public void setRecvUserId(String recvUserId) {
        this.recvUserId = recvUserId;
    }

    public String getReccvUserName() {
        return reccvUserName;
    }

    public void setReccvUserName(String reccvUserName) {
        this.reccvUserName = reccvUserName;
    }

}

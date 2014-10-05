package com.runssnail.monolith.weixin.client.refund;

import com.runssnail.monolith.common.DomainBase;

/**
 * �˿��������ݶ���
 * 
 * @author zhengwei
 */
public class RefundApply extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = -8949545311970066822L;

    /**
     * �̻��� partner �� String(10) �̻���,�ɲƸ�ͨͳһ�����10 λ������ (120XXXXXXX)��
     */
    private String            partner;

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
     * �ܽ�� total_fee �� Int �����ܽ���λΪ��
     */
    private Long              totalFee;

    /**
     * �˿��� refund_fee �� Int �˿��ܽ��,��λΪ��,�����������˿�
     */
    private Long              refundFee;

    /**
     * ����Ա op_user_id �� Int ����Ա�ʺ�,Ĭ��Ϊ�̻���
     */
    private Long              opUserId;

    /**
     * ����Ա���� op_user_passwd �� String(32) ����Ա����,Ĭ��Ϊ�̻���̨��¼���� version Ϊ1.0 ʱ������Ϊ���� version Ϊ1.1 ʱ������ΪMD5(����)ֵ
     */
    private String            opUserPasswd;

    /**
     * �������ʺ� recv_user_id �� String(64) ת���˿�����˿�ĲƸ�ͨ�ʺš� һ��������д��ֻ��������ʧ�ܣ��ʽ�ת���� �����ֽ��˺�ʱ����״̬Ϊת���������ѯ�� �ص� refund_status ��7
     * ��11������дԭ�˿� ���Ų���д���ֶΣ��ʽ�Ż��˵�ָ���Ƹ�ͨ �˺š�����������ֶκ���
     */
    private String            recvUserId;

    /**
     * ���������� reccv_user_name �� String(32) ת���˿�����˿������(��������˿�Ĳ� ��ͨ�ʺŰ󶨵�����һ��)
     */
    private String            reccvUserName;

    /**
     * ͨ���̻����� ���˿� use_spbill_no_fl ag �� Int ��ͨ���ӿ� (https://www.tenpay.com/cgi-bin/v1.0/pay _gate.cgi) ֧�����̻����������˿��ȡֵ
     * Ϊ1����ͨ�����ĵ�֧���ӿڵģ������贫ֵ��
     */
    private Integer           useSpbillNoFl;

    /**
     * �˿����� refund_type �� Int Ϊ�ջ�����1:�̻�������˿2���ֽ��ʺ� �˿ 3:�����̻����˿���̻������㣬 �����ֽ��ʺ��˿ʹ��2 ��3 ʱ������ϵ�� ��ͨ��ͨ�˹��ܡ�
     */
    private Integer           refundType;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

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

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }

    public Long getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Long opUserId) {
        this.opUserId = opUserId;
    }

    public String getOpUserPasswd() {
        return opUserPasswd;
    }

    public void setOpUserPasswd(String opUserPasswd) {
        this.opUserPasswd = opUserPasswd;
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

    public Integer getUseSpbillNoFl() {
        return useSpbillNoFl;
    }

    public void setUseSpbillNoFl(Integer useSpbillNoFl) {
        this.useSpbillNoFl = useSpbillNoFl;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

}

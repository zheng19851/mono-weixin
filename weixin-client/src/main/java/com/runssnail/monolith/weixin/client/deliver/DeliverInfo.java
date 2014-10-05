package com.runssnail.monolith.weixin.client.deliver;

import com.runssnail.monolith.common.DomainBase;

/**
 * ������Ϣ
 * 
 * @author zhengwei
 */
public class DeliverInfo extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 7265424907948395272L;

    private String            appId;

    /**
     * �û���OpenId
     */
    private String            openId;
    /**
     * ΢�Ž��׵���
     */
    private String            transId;

    /**
     * ������
     */
    private String            outTradeNo;

    /**
     * ����ʱ���������ָ����Linux ʱ�����
     */
    private long              deliverTimestamp;

    /**
     * ����״̬��1 �����ɹ���0 ����ʧ�ܣ�ʧ��ʱ��Ҫ��deliver_msg ����ʧ��ԭ��
     */
    private int               deliverStatus    = 1;

    /**
     * deliver_msg �Ƿ���״̬��Ϣ��ʧ��ʱ��������UTF8 ����Ĵ�����ʾ��Ϣ�����硰����Ʒ���˿��
     */
    private String            deliverMsg;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public long getDeliverTimestamp() {
        return deliverTimestamp;
    }

    public void setDeliverTimestamp(long deliverTimestamp) {
        this.deliverTimestamp = deliverTimestamp;
    }

    public int getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(int deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public String getDeliverMsg() {
        return deliverMsg;
    }

    public void setDeliverMsg(String deliverMsg) {
        this.deliverMsg = deliverMsg;
    }

}

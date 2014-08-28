package com.kongur.monolith.weixin.client.deliver;

import com.kongur.monolith.common.DomainBase;

/**
 * 发货信息
 * 
 * @author zhengwei
 */
public class DeliverInfo extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 7265424907948395272L;

    /**
     * 用户的OpenId
     */
    private String            openId;
    /**
     * 微信交易单号
     */
    private String            transId;

    /**
     * 订单号
     */
    private String            outTradeNo;

    /**
     * 发货时间戳，这里指的是Linux 时间戳；
     */
    private long              deliverTimestamp;

    /**
     * 发货状态，1 表明成功，0 表明失败，失败时需要在deliver_msg 填上失败原因；
     */
    private int               deliverStatus    = 1;

    /**
     * deliver_msg 是发货状态信息，失败时可以填上UTF8 编码的错误提示信息，比如“该商品已退款”；
     */
    private String            deliverMsg;

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

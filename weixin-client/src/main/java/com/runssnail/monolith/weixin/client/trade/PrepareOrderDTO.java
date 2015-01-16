package com.runssnail.monolith.weixin.client.trade;

import com.runssnail.monolith.common.DomainBase;

/**
 * ����Ԥ֧�����󷵻ص�����
 * 
 * @author zhengwei
 */
public class PrepareOrderDTO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 269558722149350544L;

    /**
     * Ԥ֧����id
     */
    private String            prepayId;

    /**
     * trade_typeΪNATIVE���з��أ��˲�����ֱ�����ɶ�ά��չʾ��������ɨ��֧��
     */
    private String            codeUrl;

    public PrepareOrderDTO(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

}

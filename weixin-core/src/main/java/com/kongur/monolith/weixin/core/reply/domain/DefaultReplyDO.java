package com.kongur.monolith.weixin.core.reply.domain;

import com.kongur.monolith.common.DomainBase;

/**
 * Ĭ�ϻظ���Ϣ���ݶ���
 * 
 * @author zhengwei
 */
public class DefaultReplyDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 902057205152868107L;

    /**
     * ����
     */
    private String            content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

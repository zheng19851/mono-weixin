package com.runssnail.monolith.weixin.core.reply.domain;

import com.runssnail.monolith.common.DomainBase;

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

    private String            appId;

    /**
     * ����
     */
    private String            content;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void copyFrom(DefaultReplyDO newReply) {
        this.appId = newReply.appId;
        this.content = newReply.content;

    }

}

package com.kongur.monolith.weixin.core.reply.domain;

import com.kongur.monolith.common.DomainBase;

/**
 * @author zhengwei
 */
public class ErrorReplyDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 902057205152868107L;

    /**
     * ÄÚÈÝ
     */
    private String            content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

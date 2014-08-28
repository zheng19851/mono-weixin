package com.kongur.monolith.weixin.core.reply.domain;

import com.kongur.monolith.common.DomainBase;

/**
 * 默认回复消息数据对象
 * 
 * @author zhengwei
 */
public class DefaultReplyDO extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 902057205152868107L;

    /**
     * 内容
     */
    private String            content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

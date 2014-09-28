package com.kongur.monolith.weixin.client.custom.message;

import java.util.List;

import com.kongur.monolith.weixin.client.common.EnumMessageType;

/**
 * Í¼ÎÄÏûÏ¢
 * 
 * @author zhengwei
 */
public class NewsCustomMessage extends AbstractCustomMessage {

    /**
     * 
     */
    private static final long serialVersionUID = -7811389785316919305L;

    private List<Article>     articels;

    public NewsCustomMessage(String appId, String toUser) {
        super(EnumMessageType.NEWS.getValue(), appId, toUser);
    }

    public List<Article> getArticels() {
        return articels;
    }

    public void setArticels(List<Article> articels) {
        this.articels = articels;
    }

}

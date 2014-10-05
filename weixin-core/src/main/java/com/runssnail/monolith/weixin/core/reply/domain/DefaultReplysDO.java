package com.runssnail.monolith.weixin.core.reply.domain;

import java.util.ArrayList;
import java.util.List;

import com.runssnail.monolith.common.DomainBase;

public class DefaultReplysDO extends DomainBase {

    /**
     * 
     */
    private static final long    serialVersionUID = -6498424006895964318L;

    private List<DefaultReplyDO> replyList;

    public List<DefaultReplyDO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<DefaultReplyDO> replyList) {
        this.replyList = replyList;
    }

    public boolean isEmpty() {
        return this.replyList == null || this.replyList.isEmpty();
    }

    public int size() {
        return this.replyList != null ? this.replyList.size() : 0;
    }

    public void add(DefaultReplyDO defaultReply) {
        if (this.replyList == null) {
            this.replyList = new ArrayList<DefaultReplyDO>();
        }
        this.replyList.add(defaultReply);

    }

}

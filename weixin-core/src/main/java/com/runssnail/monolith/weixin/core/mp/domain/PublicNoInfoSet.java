package com.runssnail.monolith.weixin.core.mp.domain;

import java.util.List;

import com.runssnail.monolith.common.DomainBase;
;

/**
 * 公众号信息集合
 * 
 * @author zhengwei
 */
public class PublicNoInfoSet extends DomainBase {

    /**
     * 
     */
    private static final long    serialVersionUID = -5724600001345412083L;
    private List<PublicNoInfoDO> publicNoInfoList;

    public List<PublicNoInfoDO> getPublicNoInfoList() {
        return publicNoInfoList;
    }

    public void setPublicNoInfoList(List<PublicNoInfoDO> publicNoInfoList) {
        this.publicNoInfoList = publicNoInfoList;
    }

    public boolean isNotEmpty() {
        return this.publicNoInfoList != null && !this.publicNoInfoList.isEmpty();
    }

    public int size() {
        return this.publicNoInfoList == null ? 0 : this.publicNoInfoList.size();
    }

}

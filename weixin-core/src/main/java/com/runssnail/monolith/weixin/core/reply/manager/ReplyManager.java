package com.runssnail.monolith.weixin.core.reply.manager;

import com.runssnail.monolith.weixin.core.reply.domain.ReplyDO;
import com.runssnail.monolith.weixin.core.reply.domain.ReplysDO;

/**
 * 回复管理
 * 
 * @author zhengwei
 * 
 * @date 2014年2月20日
 * 
 */
public interface ReplyManager {

	/**
	 * 获取所有回复设置
	 * 
	 * @return
	 */
	ReplysDO getReplys();

	/**
	 * 获取回复记录
	 * 
	 * @param replyId
	 * @return
	 */
	ReplyDO getReply(String replyId);

	/**
	 * 删除
	 * 
	 * @param replyId
	 * @return
	 */
	boolean remove(String replyId);

	/**
	 * 修改
	 * 
	 * @param repaly
	 * @return
	 */
	boolean update(ReplyDO repaly);

	/**
	 * 新增
	 * 
	 * @param repaly
	 * @return
	 */
	String addReply(ReplyDO repaly);

}

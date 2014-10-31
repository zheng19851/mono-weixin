package com.runssnail.monolith.weixin.core.reply.manager;

import com.runssnail.monolith.weixin.core.reply.domain.ReplyDO;
import com.runssnail.monolith.weixin.core.reply.domain.ReplysDO;

/**
 * �ظ�����
 * 
 * @author zhengwei
 * 
 * @date 2014��2��20��
 * 
 */
public interface ReplyManager {

	/**
	 * ��ȡ���лظ�����
	 * 
	 * @return
	 */
	ReplysDO getReplys();

	/**
	 * ��ȡ�ظ���¼
	 * 
	 * @param replyId
	 * @return
	 */
	ReplyDO getReply(String replyId);

	/**
	 * ɾ��
	 * 
	 * @param replyId
	 * @return
	 */
	boolean remove(String replyId);

	/**
	 * �޸�
	 * 
	 * @param repaly
	 * @return
	 */
	boolean update(ReplyDO repaly);

	/**
	 * ����
	 * 
	 * @param repaly
	 * @return
	 */
	String addReply(ReplyDO repaly);

}
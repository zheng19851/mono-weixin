package com.runssnail.monolith.weixin.client.deliver;

import com.runssnail.monolith.common.result.Result;

/**
 * ����֪ͨ����֪΢�ź�̨�ö����ķ���״̬
 * <p>
 * ����ʱ�����ƣ����⡢������24Сʱ�ڣ�ʵ����72Сʱ�ڡ� �����յ�֧��֪ͨ�󣬰�ʱ��������ʹ�÷���֪ͨ�ӿڽ������Ϣͬ����΢�ź�̨���� ƽ̨�ڹ涨ʱ����û���յ���������������ʱ����
 * </p>
 * 
 * @author zhengwei
 */
public interface IDeliverNotifyService {

    /**
     * ����֪ͨ
     * 
     * @param deliverInfo
     * @return
     */
    Result<Object> sendNotify(DeliverInfo deliverInfo);
}

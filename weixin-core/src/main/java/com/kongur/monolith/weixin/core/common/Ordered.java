package com.kongur.monolith.weixin.core.common;

/**
 * ��Ҫ������Ķ������ʵ�ִ˽ӿ�
 * 
 * @author zhengwei
 * @date 2014-2-19
 */
public interface Ordered {

    /**
     * ������ȼ�
     */
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * ������ȼ�
     */
    int LOWEST_PRECEDENCE  = Integer.MAX_VALUE;

    /**
     * ����ֵ
     * 
     * @return
     */
    int getOrder();

}

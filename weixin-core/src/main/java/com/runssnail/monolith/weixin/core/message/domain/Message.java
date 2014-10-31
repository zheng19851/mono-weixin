package com.runssnail.monolith.weixin.core.message.domain;

import java.util.Map;

import com.runssnail.monolith.weixin.core.message.domain.features.Features;

/**
 * ���յ�����Ϣ
 * 
 * @author zhengwei
 * @date 2014-2-14
 */
public interface Message<F extends Features> {

    Message<Features> NULL_MESSAGE = new Message<Features>() {

                             @Override
                             public String getToUserName() {
                                 return null;
                             }

                             @Override
                             public String getTimestamp() {
                                 return null;
                             }

                             @Override
                             public String getString(String key) {
                                 return null;
                             }

                             @Override
                             public String getSignature() {
                                 return null;
                             }

                             @Override
                             public Map<String, Object> getParams() {
                                 return null;
                             }

                             @Override
                             public Object getParam(String key) {
                                 return null;
                             }

                             @Override
                             public String getNonce() {
                                 return null;
                             }

                             @Override
                             public String getMsgType() {
                                 return null;
                             }

                             @Override
                             public String getFromUserName() {
                                 return null;
                             }

                             @Override
                             public long getCreateTime() {
                                 return -1;
                             }

                             @Override
                             public boolean containsKey(String key) {
                                 return false;
                             }

                             @Override
                             public String getMsgId() {
                                 return null;
                             }

                             @Override
                             public boolean isValid() {
                                 return false;
                             }

                             @Override
                             public String getAppId() {
                                 return null;
                             }

                             @Override
                             public Features getFeatures() {
                                 return null;
                             }

                         };

    String getAppId();

    /**
     * ��ϢID
     */
    String getMsgId();

    /**
     * ������Ϣ����
     * 
     * @return
     */
    String getMsgType();

    /**
     * ����ǩ��
     * 
     * @return
     */
    String getSignature();

    /**
     * ʱ���
     * 
     * @return
     */
    String getTimestamp();

    /**
     * �����
     * 
     * @return
     */
    String getNonce();

    /**
     * ����Ϣ��
     * 
     * @return
     */
    String getFromUserName();

    /**
     * ��ȡ����Ϣ��
     * 
     * @return
     */
    String getToUserName();

    /**
     * ����ʱ��
     * 
     * @return
     */
    long getCreateTime();

    /**
     * ��Ϣҵ������
     * 
     * @return
     */
    Map<String, Object> getParams();

    /**
     * ��ȡҵ������
     * 
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * ��ȡҵ������
     * 
     * @param key
     * @return
     */
    Object getParam(String key);

    /**
     * �Ƿ�����������
     * 
     * @param key
     * @return
     */
    boolean containsKey(String key);

    /**
     * �Ƿ���ȷ
     * 
     * @return
     */
    boolean isValid();

    /**
     * ����Ϣ���Ե��������ԣ���װ��json����
     * 
     * @return
     */
    F getFeatures();
}
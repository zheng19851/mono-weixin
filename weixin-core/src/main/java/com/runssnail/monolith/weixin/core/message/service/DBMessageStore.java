package com.runssnail.monolith.weixin.core.message.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.runssnail.monolith.lang.StringUtil;
import com.runssnail.monolith.weixin.core.message.dao.WXMessageDAO;
import com.runssnail.monolith.weixin.core.message.domain.Message;
import com.runssnail.monolith.weixin.core.message.domain.WXMessageDO;
import com.runssnail.monolith.weixin.core.message.domain.features.Features;

/**
 * ���ݿ�ʵ��
 * 
 * @author zhengwei
 */
@Service("dbMessageStore")
@Profile("msgUseDB")
public class DBMessageStore extends AbstractMessageStore {

    private static final String NAME                       = "db";

    @Autowired
    private WXMessageDAO        messageDAO;

    /**
     * �Ƿ�����Ϣ�Ƿ���ڣ��ظ����ʻ������͵ģ����ҵ�������ظ������ݣ���ô����ѡ��ر�
     */
    @Value("${weixin.message.check.disabled:false}")
    private volatile boolean    checkMessageExistsDisabled = false;

    /**
     * ֧�ִ洢����Ϣ����
     */
    @Value("${weixin.message.store.types:text,event,voice,image,video,location,link}")
    private String              supportedMessageTypesStr;

    /**
     * ֧�ִ������Ϣ����
     */
    private Set<String>         supportedMessageTypeSet    = null;

    public DBMessageStore() {
        super(NAME);
    }

    @PostConstruct
    public void init() {

        if (log.isDebugEnabled()) {
            log.debug("supportedMessageTypesStr=" + supportedMessageTypesStr);
        }

        if (StringUtil.isNotBlank(this.supportedMessageTypesStr)) {

            String[] supportedMessageTypesArr = this.supportedMessageTypesStr.split(",");
            supportedMessageTypeSet = new HashSet<String>(supportedMessageTypesArr.length);
            for (String msgType : supportedMessageTypesArr) {
                supportedMessageTypeSet.add(msgType);
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("supportedMessageTypeSet=" + supportedMessageTypeSet);
        }

    }

    @Override
    public String store(Message<Features> msg) {
        if (supportsMessage(msg)) {
            WXMessageDO message = new WXMessageDO(msg);
            Long id = messageDAO.insertMessage(message);
            return String.valueOf(id);
        }

        String msgId = msg.getMsgId();
        if (StringUtil.isNotBlank(msgId)) {
            return msgId;
        } else {
            return msg.getFromUserName() + "_" + msg.getCreateTime();
        }
    }

    /**
     * �Ƿ�֧�ִ���ǰ��Ϣ
     * 
     * @param msg
     * @return
     */
    private boolean supportsMessage(Message<Features> msg) {
        if (this.supportedMessageTypeSet == null) {
            return true;
        }

        return this.supportedMessageTypeSet.contains(msg.getMsgType());
    }

    @Override
    public boolean contains(Message<Features> msg) {
        if (this.checkMessageExistsDisabled) {
            return false;
        }

        WXMessageDO message = null;
        if (StringUtil.isNotBlank(msg.getMsgId())) {
            message = messageDAO.selectMessageByMsgId(msg.getMsgId());
        } else {
            message = messageDAO.selectMessage(msg.getFromUserName(), msg.getCreateTime());
        }
        return message != null;

    }

}

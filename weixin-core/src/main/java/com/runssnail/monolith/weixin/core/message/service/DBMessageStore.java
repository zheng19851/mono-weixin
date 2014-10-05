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
 * 数据库实现
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
     * 是否检查消息是否存在，重复概率还是蛮低的，如果业务允许重复的数据，那么可以选择关闭
     */
    @Value("${weixin.message.check.disabled:false}")
    private volatile boolean    checkMessageExistsDisabled = false;

    /**
     * 支持存储的消息类型
     */
    @Value("${weixin.message.store.types:text,event,voice,image,video,location,link}")
    private String              supportedMessageTypesStr;

    /**
     * 支持处理的消息类型
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
     * 是否支持处理当前消息
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

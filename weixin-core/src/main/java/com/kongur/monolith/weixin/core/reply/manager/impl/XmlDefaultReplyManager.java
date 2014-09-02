package com.kongur.monolith.weixin.core.reply.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kongur.monolith.weixin.client.support.RemoteAppEventService;
import com.kongur.monolith.weixin.core.reply.domain.DefaultReplyDO;
import com.kongur.monolith.weixin.core.reply.domain.DefaultReplysDO;
import com.kongur.monolith.weixin.core.reply.manager.DefaultReplyManager;
import com.thoughtworks.xstream.XStream;

/**
 * @author zhengwei
 */
@Service("defaultReplyManager")
public class XmlDefaultReplyManager implements DefaultReplyManager {

    private final Logger                log                  = Logger.getLogger(getClass());

    /**
     * Â·¾¶
     */
    @Value("${weixin.reply.error.conf}")
    private String                      confPath;

    private File                        file                 = null;

    private XStream                     xStream;

    /**
     * »º´æ
     */
    private DefaultReplysDO             defaultReplysCache;

    private Map<String, DefaultReplyDO> defaultReplyMapCache = new HashMap<String, DefaultReplyDO>();

    @Autowired
    private RemoteAppEventService       remoteAppEventService;

    @PostConstruct
    public void init() throws IOException {

        Assert.notNull(this.confPath, "the xml conf file of default reply can not be null.");
        File file = new File(this.confPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.file = file;

        if (log.isDebugEnabled()) {
            log.debug("the xml conf file of default reply is " + this.confPath);
        }

        if (xStream == null) {
            xStream = new XStream();
            xStream.alias("reply", DefaultReplyDO.class);
            xStream.alias("replys", DefaultReplysDO.class);

            xStream.addImplicitCollection(DefaultReplysDO.class, "replyList");

        }

        refresh();

        if (log.isDebugEnabled()) {
            log.debug("init ErrorReplyDO successfully, defaultReplysCache=" + defaultReplysCache);
        }
    }

    @Override
    public DefaultReplyDO getDefaultReply(String appId) {

        return this.defaultReplyMapCache != null ? this.defaultReplyMapCache.get(appId) : null;
    }

    @Override
    public void refresh() {

        if (this.file.length() <= 0) {
            log.warn("there is no error reply need to refresh.");
            this.defaultReplysCache = null;
            this.defaultReplyMapCache = null;
            return;
        }

        DefaultReplysDO defaultReplys = null;
        try {
            defaultReplys = (DefaultReplysDO) xStream.fromXML(new FileInputStream(this.file));
        } catch (FileNotFoundException e) {
            log.error("can not find the error reply conf file", e);
            return;
        }

        this.defaultReplysCache = defaultReplys;
        if (defaultReplys != null && !defaultReplys.isEmpty()) {
            Map<String, DefaultReplyDO> defaultReplyMap = new HashMap<String, DefaultReplyDO>(defaultReplys.size());
            for (DefaultReplyDO reply : defaultReplys.getReplyList()) {
                defaultReplyMap.put(reply.getAppId(), reply);
            }
            this.defaultReplyMapCache = defaultReplyMap;
        }

        if (log.isDebugEnabled()) {
            log.debug("refresh DefaultReplysDO successfully, DefaultReplysDO=" + defaultReplys);
        }
    }

}

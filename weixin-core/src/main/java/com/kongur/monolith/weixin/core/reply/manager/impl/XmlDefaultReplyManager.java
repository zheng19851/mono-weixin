package com.kongur.monolith.weixin.core.reply.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kongur.monolith.weixin.client.support.EnumAppEventType;
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

    private ReentrantReadWriteLock      readWriteLock        = new ReentrantReadWriteLock();

    private WriteLock                   wl                   = readWriteLock.writeLock();
    private ReadLock                    rl                   = readWriteLock.readLock();

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

        rl.lock();
        try {
            return this.defaultReplyMapCache != null ? this.defaultReplyMapCache.get(appId) : null;
        } finally {
            rl.unlock();
        }
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

    @Override
    public boolean update(DefaultReplyDO newReply) {

        wl.lock();

        try {

            if (this.defaultReplysCache == null) {
                this.defaultReplysCache = new DefaultReplysDO();
                this.defaultReplysCache.add(newReply);
            } else {
                if (!this.defaultReplysCache.isEmpty()) {
                    DefaultReplyDO old = null;
                    for (DefaultReplyDO reply : this.defaultReplysCache.getReplyList()) {
                        if (newReply.getAppId().equals(reply.getAppId())) {
                            old = reply;
                            break;
                        }
                    }

                    if (old != null) {
                        old.copyFrom(newReply);
                    } else {
                        this.defaultReplysCache.add(newReply);
                    }
                }
            }

            if (this.defaultReplyMapCache == null) {
                this.defaultReplyMapCache = new HashMap<String, DefaultReplyDO>();
            }
            this.defaultReplyMapCache.put(newReply.getAppId(), newReply);

            storeToXml();
        } finally {
            wl.unlock();
        }

        remoteAppEventService.multicastEvent(EnumAppEventType.REFRESH_ERROR_REPLY);

        return true;
    }

    private void storeToXml() {
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            this.xStream.toXML(this.defaultReplysCache, out);
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("can not find the default reply conf file, filePath=" + this.confPath, e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }

    }

}

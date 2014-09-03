package com.kongur.monolith.weixin.core.reply.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import com.kongur.monolith.weixin.client.support.RemoteAppEventService;
import com.kongur.monolith.weixin.core.menu.domain.ItemDO;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;
import com.kongur.monolith.weixin.core.reply.domain.ReplysDO;
import com.kongur.monolith.weixin.core.reply.domain.SubscribeReplyDO;
import com.kongur.monolith.weixin.core.reply.manager.SubscribeReplyManager;
import com.thoughtworks.xstream.XStream;

/**
 * 用xml实现的订阅回复管理服务
 * 
 * @author zhengwei
 * @date 2014年2月21日
 */
@Service("xmlSubscribeReplyManager")
public class XmlSubscribeReplyManager implements SubscribeReplyManager {

    private final Logger           log           = Logger.getLogger(getClass());

    /**
     * 路径
     */
    @Value("${weixin.subscribe.reply.conf}")
    private String                 confPath;

    private File                   file;

    /**
     * xml与对象之间转换用
     */
    private XStream                xstream;

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private ReplysDO               replysCache;
    /**
     * key = appid
     */
    private Map<String, ReplyDO>   replyMapCache = new HashMap<String, ReplyDO>();

    @Autowired
    private RemoteAppEventService  remoteAppEventService;

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(this.confPath);

        if (!this.file.exists()) {
            this.file.createNewFile();
        }

        if (xstream == null) {
            xstream = new XStream();
            xstream.alias("reply", SubscribeReplyDO.class);
            xstream.alias("item", ItemDO.class);
            xstream.alias("replys", ReplysDO.class);

            xstream.addImplicitCollection(ReplysDO.class, "replys");
        }

        refresh();
    }

    /**
     * 刷新
     */
    public void refresh() {

        if (this.file.length() <= 0) {
            log.warn("there are no subscribe reply to refresh.");
            return;
        }

        // 将XML文件数据转成java对像
        ReplysDO replys = getReplysFromXml();

        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {
            this.replysCache = replys;
            // if (this.replysCache == null) {
            // this.replysCache = new ReplysDO();
            // }

            // 先清除缓存
            this.replyMapCache.clear();

            if (replys != null && replys.getReplys() != null && !replys.getReplys().isEmpty()) {
                for (ReplyDO reply : replys.getReplys()) {
                    this.replyMapCache.put(reply.getAppId(), reply);
                }
            }

        } finally {
            writeLock.unlock();
        }

        if (log.isDebugEnabled()) {
            log.debug("refresh subscribe reply successfully, replys=" + replys);
        }

    }

    private ReplysDO getReplysFromXml() {

        if (this.file.length() <= 0) {
            log.warn("there are no subscribe reply to refresh.");
            return null;
        }

        ReplysDO replys = null;
        FileInputStream in = null;
        try {

            in = new FileInputStream(file);
            replys = (ReplysDO) xstream.fromXML(in);
        } catch (IOException e) {
            throw new RuntimeException("get subscribe reply from xml error", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
        return replys;
    }

    public XmlSubscribeReplyManager() {
    }

    @Override
    public SubscribeReplyDO getSubscribeReply(String appId) {
        ReadLock readLock = readWriteLock.readLock();
        readLock.lock();

        try {

            return (SubscribeReplyDO) this.replyMapCache.get(appId);

        } finally {
            readLock.unlock();
        }
    }

    @Override
    public ReplysDO getSubscribeReplys() {
        return this.replysCache;
    }

}

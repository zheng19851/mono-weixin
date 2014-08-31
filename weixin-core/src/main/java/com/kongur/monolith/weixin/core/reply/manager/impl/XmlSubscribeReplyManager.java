package com.kongur.monolith.weixin.core.reply.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.Date;
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

import com.eyeieye.skyjoo.util.StringUtil;
import com.eyeieye.skyjoo.util.UUIDGenerator;
import com.kongur.monolith.weixin.client.support.EnumAppEventType;
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

    private String                 fileEncoding  = "UTF-8";

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
        ReplysDO replys = null;
        FileInputStream in = null;
        try {

            in = new FileInputStream(file);
            replys = (ReplysDO) xstream.fromXML(in);
        } catch (IOException e) {
            throw new RuntimeException("refresh subscribe reply error", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }

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

    public XmlSubscribeReplyManager() {
    }

    @Override
    public String create(SubscribeReplyDO reply) {

        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {

            this.replysCache.addReply(reply);
            this.replyMapCache.put(reply.getAppId(), reply);

            storeToXml();

        } finally {
            writeLock.unlock();
        }

        remoteAppEventService.multicastEvent(EnumAppEventType.REFRESH_SUBSCRIBE_REPLY);

        return reply.getId();
    }

    private void storeToXml() {

        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file), this.fileEncoding);
            xstream.toXML(this.replysCache, out);
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("can not find the subscribe reply conf file, filePath=" + this.confPath, e);
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
    public boolean update(SubscribeReplyDO reply) {
        if (reply == null) {
            return false;
        }

        // 将之前的信息提交上来的话，就清理掉
        if (reply.isResource()) {
            reply.setItems(null);
            reply.setContent(null);
        } else if (reply.isNews()) {
            reply.setContent(null);
            reply.setResourceId(null);
        } else if (reply.isText()) {
            reply.setResourceId(null);
            reply.setItems(null);
        }

        if (StringUtil.isBlank(reply.getId())) {
            String id = UUIDGenerator.generate();
            reply.setId(id);
        }

        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {
            reply.setGmtModify(new Date());
            if (this.replysCache == null) {
                this.replysCache = new ReplysDO();
                this.replysCache.addReply(reply);
            } else {
                ReplyDO old = null;

                for (ReplyDO r : this.replysCache.getReplys()) {
                    if (r.getId().equals(reply.getId())) {
                        old = r;
                        break;
                    }
                }
                if (old != null) {
                    old.copyFrom(reply);
                } else {
                    this.replysCache.addReply(reply);
                }
            }

            this.replyMapCache.put(reply.getAppId(), reply);

            storeToXml();

        } finally {
            writeLock.unlock();
        }

        // 刷新微信平台上的本地缓存
        remoteAppEventService.multicastEvent(EnumAppEventType.REFRESH_SUBSCRIBE_REPLY);

        return true;
    }

    @Override
    public boolean remove() {

        // WriteLock writeLock = readWriteLock.writeLock();
        // writeLock.lock();
        //
        // try {
        //
        // this.replyCache = null;
        //
        // clearFile();
        //
        // } finally {
        // writeLock.unlock();
        // }
        //
        // remoteAppEventService.multicastEvent(EnumAppEventType.REFRESH_SUBSCRIBE_REPLY);

        return false;
    }

    private void clearFile() {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.file, "rw");
            raf.setLength(0);
            raf.close();
        } catch (Exception e) {

            throw new RuntimeException("clear xml error", e);
        }

    }

    @Override
    public ReplysDO getSubscribeReplys() {
        return this.replysCache;
    }

}

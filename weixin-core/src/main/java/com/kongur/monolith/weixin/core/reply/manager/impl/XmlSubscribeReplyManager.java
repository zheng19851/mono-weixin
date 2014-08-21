package com.kongur.monolith.weixin.core.reply.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kongur.monolith.weixin.core.menu.domain.ItemDO;
import com.kongur.monolith.weixin.core.reply.domain.SubscribeReplyDO;
import com.kongur.monolith.weixin.core.reply.manager.SubscribeReplyManager;
import com.thoughtworks.xstream.XStream;

/**
 * ��xmlʵ�ֵĶ��Ļظ��������
 * 
 * @author zhengwei
 * @date 2014��2��21��
 */
@Service("xmlSubscribeReplyManager")
public class XmlSubscribeReplyManager implements SubscribeReplyManager {

    private final Logger           log           = Logger.getLogger(getClass());

    /**
     * ·��
     */
    @Value("${weixin.subscribe.reply.conf}")
    private String                 confPath;

    private File                   file;

    /**
     * xml�����֮��ת����
     */
    private XStream                xstream;

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private SubscribeReplyDO       replyCache;

    private String                 fileEncoding  = "UTF-8";

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
        }

        refresh();
    }

    /**
     * ˢ��
     */
    public void refresh() {

        if (this.file.length() <= 0) {
            log.warn("there are no subscribe reply to refresh.");
            return;
        }

        // ��XML�ļ�����ת��java����
        SubscribeReplyDO reply = null;
        try {

            FileInputStream in = new FileInputStream(file);
            reply = (SubscribeReplyDO) xstream.fromXML(in);
        } catch (IOException e) {
            throw new RuntimeException("refresh subscribe reply error", e);
        }

        if (reply == null) {
            return;
        }

        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {

            this.replyCache = reply;

        } finally {
            writeLock.unlock();
        }

        if (log.isDebugEnabled()) {
            log.debug("refresh subscribe reply successfully, reply=" + this.replyCache);
        }

    }

    public XmlSubscribeReplyManager() {
    }

    @Override
    public String create(SubscribeReplyDO reply) {

        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {

            this.replyCache = reply;

            storeToXml(reply);

        } finally {
            writeLock.unlock();
        }

        return reply.getId();
    }

    private void storeToXml(SubscribeReplyDO reply) {

        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file), this.fileEncoding);
        } catch (Exception e) {
            throw new RuntimeException("can not find the subscribe reply conf file, filePath=" + this.confPath, e);
        }

        xstream.toXML(reply, out);
    }

    @Override
    public SubscribeReplyDO getSubscribeReply() {
        return this.replyCache;
    }

    @Override
    public boolean update(SubscribeReplyDO reply) {
        if (reply == null) {
            return false;
        }

        // ��֮ǰ����Ϣ�ύ�����Ļ����������
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

        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {

            this.replyCache = reply;

            storeToXml(reply);

        } finally {
            writeLock.unlock();
        }

        return true;
    }

    @Override
    public boolean remove() {

        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        try {

            this.replyCache = null;

            clearFile();

        } finally {
            writeLock.unlock();
        }

        return true;
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

}

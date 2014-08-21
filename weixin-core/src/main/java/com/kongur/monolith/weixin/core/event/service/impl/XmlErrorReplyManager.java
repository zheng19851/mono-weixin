package com.kongur.monolith.weixin.core.event.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.kongur.monolith.weixin.common.service.RemoteAppEventService;
import com.kongur.monolith.weixin.core.event.domain.ErrorReplyDO;
import com.kongur.monolith.weixin.core.event.service.ErrorReplyManager;
import com.thoughtworks.xstream.XStream;

/**
 * @author zhengwei
 */
@Service("errorReplyManager")
public class XmlErrorReplyManager implements ErrorReplyManager {

    private final Logger          log  = Logger.getLogger(getClass());

    /**
     * Â·¾¶
     */
    @Value("${weixin.reply.error.conf}")
    private String                confPath;

    private File                  file = null;

    private XStream               xStream;

    /**
     * »º´æ
     */
    private ErrorReplyDO          errorReply;

    @Autowired
    private RemoteAppEventService remoteAppEventService;

    @PostConstruct
    public void init() throws IOException {

        Assert.notNull(this.confPath, "the xml conf file of error reply can not be null.");
        File file = new File(this.confPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        this.file = file;

        if (log.isDebugEnabled()) {
            log.debug("the xml conf file of error reply is " + this.confPath);
        }

        if (xStream == null) {
            xStream = new XStream();
            xStream.alias("errorReply", ErrorReplyDO.class);
        }

        refresh();

        if (log.isDebugEnabled()) {
            log.debug("init ErrorReplyDO successfully, ErrorReplyDO=" + errorReply);
        }
    }

    @Override
    public ErrorReplyDO getErrorReply() {
        return this.errorReply;
    }

    @Override
    public void refresh() {

        if (this.file.length() <= 0) {
            log.warn("there is no error reply need to refresh.");
            this.errorReply = new ErrorReplyDO();
            return;
        }

        ErrorReplyDO errorReply = null;
        try {
            errorReply = (ErrorReplyDO) xStream.fromXML(new FileInputStream(this.file));
        } catch (FileNotFoundException e) {
            log.error("can not find the error reply conf file", e);
            return;
        }

        this.errorReply = errorReply;

        if (log.isDebugEnabled()) {
            log.debug("refresh ErrorReplyDO successfully, ErrorReplyDO=" + errorReply);
        }
    }

    // @Override
    // public boolean update(ErrorReplyDO errorReply) {
    //
    // OutputStreamWriter out = null;
    // try {
    // out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
    // } catch (Exception e) {
    // throw new RuntimeException("can not find the error reply conf file, filePath=" + this.confPath, e);
    // }
    //
    // this.xStream.toXML(errorReply, out);
    //
    // this.errorReply = errorReply;
    //
    // remoteAppEventService.onEvent(EnumAppEventType.REFRESH_ERROR_REPLY);
    //
    // return true;
    // }

}

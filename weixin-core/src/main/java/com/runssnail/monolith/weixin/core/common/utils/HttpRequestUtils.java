package com.runssnail.monolith.weixin.core.common.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class HttpRequestUtils {

    private static final Logger log = Logger.getLogger(HttpRequestUtils.class);

    public static String readMsg(HttpServletRequest req) {
        String receivedMsg = null;

        int len = req.getContentLength();

        if (len <= 0) {
            log.error("can not find any content in the request. len=" + len);
            return null;
        }

        try {

            InputStream in = req.getInputStream();
            byte[] dataBytes = new byte[len];
            in.read(dataBytes);

            receivedMsg = new String(dataBytes);
            if (log.isDebugEnabled()) {
                log.debug("received message->" + receivedMsg + "<-");
            }

        } catch (IOException e) {
            log.error("read receivedMsg error, req=" + req, e);
            return null;
        }

        return receivedMsg;
    }

}

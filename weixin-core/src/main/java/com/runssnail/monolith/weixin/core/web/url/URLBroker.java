package com.runssnail.monolith.weixin.core.web.url;

import java.lang.reflect.Method;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

public class URLBroker implements ServletContextAware, InitializingBean {

    private final Log      logger = LogFactory.getLog(this.getClass());

    private URLConfig      config;

    // 不以"/" 结尾
    private String         server = null;

    private ServletContext servletContext;

    /**
     * Servlet 2.5 的servletContext增加了 getContextPath()方法，可以在启动时就初始化linkBuilder
     */
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;

    }

    public void init(String contextPath) {
        if (this.server != null) {// 已经init过了
            return;
        }
        StringBuilder server = new StringBuilder();
        server.append(config.getURL());
        if (config.isFollowContextPath()) {
            server.append(contextPath);
        }
        if (server.charAt(server.length() - 1) == '/') {
            server.deleteCharAt(server.length() - 1);
        }

        if (StringUtils.isNotBlank(config.getPath())) {
            if (config.getPath().startsWith("/")) {
                server.append(config.getPath());
            } else {
                server.append('/').append(config.getPath());
            }
        }

        if (server.charAt(server.length() - 1) == '/') {
            server.deleteCharAt(server.length() - 1);
        }
        this.server = server.toString();
        if (logger.isDebugEnabled()) {
            logger.debug("init end,server:" + this.server);
        }
    }

    @Override
    public String toString() {
        return this.server;
    }

    public URLConfig getConfig() {
        return config;
    }

    public void setConfig(URLConfig config) {
        this.config = config;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        try {
            Method getContextPath = ServletContext.class.getDeclaredMethod("getContextPath", new Class[] {});
            if (getContextPath != null) {
                String contextPath = (String) getContextPath.invoke(servletContext, new Object[] {});
                if (logger.isDebugEnabled()) {
                    logger.debug("Servlet 2.5 implement find. so init context path:" + contextPath);
                }
                this.init(contextPath);
            }
        } catch (Exception e) {
            logger.error("init url broker error", e);
        }

    }
}

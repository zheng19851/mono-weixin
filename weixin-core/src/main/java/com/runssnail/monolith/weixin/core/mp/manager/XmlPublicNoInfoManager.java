package com.runssnail.monolith.weixin.core.mp.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.runssnail.monolith.weixin.core.mp.domain.PublicNoInfoDO;
import com.runssnail.monolith.weixin.core.mp.domain.PublicNoInfoSet;
import com.thoughtworks.xstream.XStream;

/**
 * 公众号管理
 * 
 * @author zhengwei
 */
@Service("xmlPublicNoInfoManager")
public class XmlPublicNoInfoManager implements PublicNoInfoManager {

    private final Logger                log                  = Logger.getLogger(getClass());

    /**
     * 配置目录路径
     */
    // @Value("${weixin.publicNo.conf}")
    @Value("${weixin.conf.rootDir}")
    private String                      confPath;

    private String                      fileName             = "publicNo.xml";

    private String                      fileRealPath;

    private File                        file                 = null;

    private XStream                     xStream;

    private Map<String, PublicNoInfoDO> publicNoInfoMapCache = null;
    private PublicNoInfoSet             publicNoInfoSetCache = null;

    @PostConstruct
    public void init() throws IOException {
        Assert.notNull(this.confPath, "the xml conf file of publicNo can not be null.");
        Assert.notNull(this.fileName, "the xml conf file name of publicNo can not be null.");

        if (!new File(this.confPath).exists()) {
            throw new FileNotFoundException("can not found the dir '" + this.confPath + "'");
        }

        if (!this.confPath.endsWith("/")) {
            this.confPath = this.confPath + "/";
        }

        this.fileRealPath = this.confPath + this.fileName;

        File file = new File(this.fileRealPath);
        if (!file.exists()) {
            file.createNewFile();
        }

        this.file = file;

        if (log.isDebugEnabled()) {
            log.debug("the xml conf file of publicNo is " + this.fileRealPath);
        }

        if (xStream == null) {
            xStream = new XStream();
            xStream.alias("publicNo", PublicNoInfoDO.class);
            xStream.alias("publicNoList", PublicNoInfoSet.class);

            xStream.addImplicitCollection(PublicNoInfoSet.class, "publicNoInfoList");
        }

        refresh();

        if (log.isDebugEnabled()) {
            log.debug("init ErrorReplyDO successfully.");
        }
    }

    public void refresh() {
        if (this.file.length() <= 0) {
            log.warn("there is no error reply need to refresh.");
            clear();
            return;
        }

        PublicNoInfoSet publicNoInfoSet = null;
        try {
            publicNoInfoSet = (PublicNoInfoSet) xStream.fromXML(new FileInputStream(this.file));
        } catch (FileNotFoundException e) {
            log.error("can not find the publicNo conf file", e);
            return;
        }

        this.publicNoInfoSetCache = publicNoInfoSet;
        if (publicNoInfoSet.isNotEmpty()) {
            Map<String, PublicNoInfoDO> publicNoInfoMap = new HashMap<String, PublicNoInfoDO>(publicNoInfoSet.size());
            for (PublicNoInfoDO info : publicNoInfoSet.getPublicNoInfoList()) {
                publicNoInfoMap.put(info.getAppId(), info);
            }
            this.publicNoInfoMapCache = publicNoInfoMap;
        }

        if (log.isDebugEnabled()) {
            log.debug("refresh publicNoInfo successfully, publicNoInfoSet=" + publicNoInfoSet);
        }
    }

    private void clear() {
        this.publicNoInfoMapCache = null;
        this.publicNoInfoSetCache = null;
    }

    @Override
    public PublicNoInfoDO getPublicNoInfoByAppId(String appId) {
        return this.publicNoInfoMapCache != null ? this.publicNoInfoMapCache.get(appId) : null;
    }

    @Override
    public String getTokenByAppId(String appId) {
        PublicNoInfoDO info = this.getPublicNoInfoByAppId(appId);

        return info != null ? info.getToken() : null;
    }

    @Override
    public String getAppSecretByAppId(String appId) {
        PublicNoInfoDO info = this.getPublicNoInfoByAppId(appId);

        return info != null ? info.getAppSecret() : null;
    }

    @Override
    public List<PublicNoInfoDO> getPublicNoInfoList() {
        return this.publicNoInfoSetCache != null ? this.publicNoInfoSetCache.getPublicNoInfoList() : null;
    }

    public static void main(String[] args) throws IOException {
        XmlPublicNoInfoManager publicNoManager = new XmlPublicNoInfoManager();
        publicNoManager.confPath = "D:/temp/weixin/publicNo.xml";
        publicNoManager.init();

        String appS = publicNoManager.getAppSecretByAppId("2J3333");
        System.out.println(appS);

        System.out.println(publicNoManager.getPublicNoInfoByAppId("2J3333"));

    }

    @Override
    public boolean exists(String appId) {
        return this.publicNoInfoMapCache != null ? this.publicNoInfoMapCache.containsKey(appId) : false;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

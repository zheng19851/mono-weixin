package com.kongur.monolith.weixin.core.menu.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import com.kongur.monolith.lang.StringUtil;
import com.kongur.monolith.weixin.core.menu.domain.ItemDO;
import com.kongur.monolith.weixin.core.menu.domain.MenuDO;
import com.kongur.monolith.weixin.core.menu.domain.MenuListDO;
import com.kongur.monolith.weixin.core.menu.domain.MenusDO;
import com.kongur.monolith.weixin.core.mp.domain.PublicNoInfoDO;
import com.kongur.monolith.weixin.core.mp.service.PublicNoInfoService;
import com.kongur.monolith.weixin.core.reply.domain.ReplyDO;
import com.thoughtworks.xstream.XStream;

/**
 * 默认用XML实现
 * 
 * @author zhengwei
 */
@Service("xmlMenuManager")
public class XmlMenuManager implements MenuManager {

    private final Logger                                                 log              = Logger.getLogger(getClass());

    @Autowired
    private PublicNoInfoService                                          publicNoInfoService;

    /**
     * 路径
     */
    // @Value("${weixin.menus.conf}")
    // private String confPath;

    // private File file;

    /**
     * 配置文件目录
     */
    // @Value("${weixin.menus.conf}")
    @Value("${weixin.conf.rootDir}")
    private String                                                       confFileDir;

    /**
     * 前缀
     */
    private String                                                       filePrefix       = "menu";

    /**
     * 文件后缀
     */
    private String                                                       fileSuffix       = ".xml";

    /**
     * 所有菜单缓存
     */
    // private MenusDO menusCache = new MenusDO(null);

    // private MenuListDO menuListCache = null;

    /**
     * key=appid
     */
    private Map<String, MenusDO>                                         menusMapCache    = new HashMap<String, MenusDO>();

    private Map<String /* appid */, Map<String /* eventKey */, ReplyDO>> allReplysCache   = new HashMap<String, Map<String /* eventKey */, ReplyDO>>();

    private Map<String /* appid */, Map<String /* menuId */, MenuDO>>    allMenusMapCache = new HashMap<String, Map<String /* menuId */, MenuDO>>();

    /**
     * xml与对象之间转换用
     */
    private XStream                                                      xstream;

    private ReentrantReadWriteLock                                       readWriteLock    = new ReentrantReadWriteLock();

    /**
     * 初始化
     * 
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        Assert.notNull(this.confFileDir, "the xml conf of menus file dir can not be null.");
        if (!this.confFileDir.endsWith("/")) {
            this.confFileDir = this.confFileDir + "/";
        }

        // this.file = new File(this.confPath);
        //
        // if (!this.file.exists()) {
        // this.file.createNewFile();
        // }

        if (xstream == null) {
            xstream = new XStream();
            xstream.alias("menus", MenusDO.class);
            xstream.alias("menu", MenuDO.class);
            xstream.alias("reply", ReplyDO.class);
            xstream.alias("item", ItemDO.class);

            // xstream.alias("menuList", MenuListDO.class); // 所有菜单

            xstream.addImplicitCollection(MenusDO.class, "menus");
            // xstream.addImplicitCollection(MenuListDO.class, "menuList");

        }

        refresh();

    }

    /**
     * 刷新单个公众号菜单
     * 
     * @param appId
     */
    public void refresh(String appId) {

        if (log.isDebugEnabled()) {
            log.debug("refresh menus start, appId=" + appId);
        }

        clear(appId);

        // 将XML文件数据转成java对像
        MenusDO menus = getMenusFromXml(appId);

        WriteLock writeLock = readWriteLock.writeLock();

        writeLock.lock();

        try {
            // 缓存回复数据
            buildCacheData(appId, menus);
        } finally {
            writeLock.unlock();
        }

        if (log.isDebugEnabled()) {
            log.debug("refresh menus successfully, appId=" + appId + ", menus=" + menus);
        }
    }

    private MenusDO getMenusFromXml(String appId) {
        MenusDO menus = null;
        String confFile = getConfFilePath(appId);

        File file = new File(confFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
                if (log.isDebugEnabled()) {
                    log.debug("create conf file successfully, appId=" + appId + ", confFile=" + confFile);
                }

                return menus;
            } catch (IOException e) {
                throw new RuntimeException("create file error, confFile=" + confFile, e);
            }
        }

        if (file.length() <= 0) {
            log.warn("there are no menus need to refresh. appId=" + appId);
            return menus;
        }

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            menus = (MenusDO) xstream.fromXML(in);
        } catch (IOException e) {
            throw new RuntimeException("refresh menus error, appId=" + appId + ", confFile=" + confFile, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }

        return menus;
    }

    private void clear(String appId) {
        WriteLock writeLock = readWriteLock.writeLock();

        writeLock.lock();

        try {
            // 清除某个appid对应的缓存数据
            Map<String /* menuId */, MenuDO> menusMap = this.allMenusMapCache.get(appId);
            if (menusMap != null) {
                menusMap.clear();
            }

            Map<String /* eventKey */, ReplyDO> replyMap = this.allReplysCache.get(appId);

            if (replyMap != null) {
                replyMap.clear();
            }

            this.menusMapCache.put(appId, null);

        } finally {
            writeLock.unlock();
        }

    }

    private String getConfFilePath(String appId) {
        return this.confFileDir + filePrefix + "_" + appId + this.fileSuffix;
    }

    public void refresh() {

        // if (this.file.length() <= 0) {
        // log.warn("there are no menus need to refresh.");
        // clear();
        // return;
        // }
        if (log.isDebugEnabled()) {
            log.debug("refresh menus start, before refresh menusMapCache=" + this.menusMapCache);
        }

        List<PublicNoInfoDO> publicNoInfoList = publicNoInfoService.getPublicNoInfoList();

        if (publicNoInfoList != null) {
            for (PublicNoInfoDO publicNoInfo : publicNoInfoList) {
                refresh(publicNoInfo.getAppId());
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("refresh menus successfully, after refresh menusMapCache=" + this.menusMapCache);
        }
    }

    /**
     * 组装回复缓存数据
     * 
     * @param appId
     * @param menus
     * @return
     */
    private void buildCacheData(String appId, MenusDO menus) {

        // if (menus.getMenus() == null) {
        // menus.setMenus(new ArrayList<MenuDO>());
        // }

        // this.menusCache = menus;

        // 缓存回复数据
        if (menus != null && !menus.isEmpty()) {
            Map<String, ReplyDO> replysMap = new HashMap<String, ReplyDO>();
            Map<String, MenuDO> menusMap = new HashMap<String, MenuDO>();

            // String suffix = appId + "_";
            for (MenuDO menu : menus.getMenus()) {
                if (menu.isClick()) {
                    ReplyDO reply = menu.getReply();
                    if (reply != null) {
                        replysMap.put(menu.getEventKey(), reply);
                        menu.setReply(reply); // 设置回复
                    }
                }

                if (menu.hasSubMenus()) {
                    for (MenuDO subMenu : menu.getSubMenus()) {
                        if (subMenu.isClick()) {
                            // ReplyDO reply = replyManager.getReply(subMenu.getReplyId());
                            ReplyDO reply = subMenu.getReply();
                            if (reply != null) {
                                replysMap.put(subMenu.getEventKey(), reply);
                                subMenu.setReply(reply);// 设置回复
                            }
                        }

                        menusMap.put(subMenu.getId(), subMenu);
                    }
                }

                menusMap.put(menu.getId(), menu);

            }

            this.allMenusMapCache.put(appId, menusMap);
            this.allReplysCache.put(appId, replysMap);

            this.menusMapCache.put(appId, menus);

            // this.menusMap = menusMap;
            //
            // this.replysCache = replysMap;

        }

    }

    @Override
    public MenusDO getMenus() {
        MenusDO menus = null;
        ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        try {

            menus = this.menusMapCache.get(publicNoInfoService.getDefaultAppId());

        } finally {
            readLock.unlock();
        }

        return menus;
    }

    @Override
    public ReplyDO getReplyByEventKey(String eventKey) {

        ReplyDO reply = null;

        ReadLock readLock = readWriteLock.readLock();

        readLock.lock();

        try {
            reply = this.allReplysCache.get(publicNoInfoService.getDefaultAppId()).get(eventKey);
            // reply = this.replysCache.get(eventKey);
        } finally {
            readLock.unlock();
        }

        return reply;
    }

    // public String getConfPath() {
    // return confPath;
    // }
    //
    // public void setConfPath(String confPath) {
    // this.confPath = confPath;
    // }

    public static void main(String[] args) throws Exception {
        readXML();

        // System.out.println("//");

    }

    private static void readXML() throws FileNotFoundException {
        XStream xstream = new XStream();
        // xstream.alias("menus", MenusDO.class);
        // xstream.alias("menu", MenuDO.class);
        //
        // xstream.addImplicitCollection(MenusDO.class, "menus");

        xstream = new XStream();
        xstream.alias("menus", MenusDO.class); // 一个公众号菜单
        xstream.alias("menu", MenuDO.class);
        xstream.alias("reply", ReplyDO.class);
        xstream.alias("item", ItemDO.class);
        xstream.alias("menuList", MenuListDO.class); // 所有菜单

        xstream.addImplicitCollection(MenusDO.class, "menus");
        xstream.addImplicitCollection(MenuListDO.class, "menuList");

        // String conf = "D:/git_repo/monolith/monolith-im/src/main/resources/menus.xml";
        String conf = "D:/temp/weixin/menus/menu_38383932929.xml";

        File f = new File(conf);

        FileInputStream in = new FileInputStream(f);

        Object obj = xstream.fromXML(in);

        System.out.println(obj);
    }

    @Override
    public MenuDO getMenu(String menuId) {
        MenuDO menu = null;

        if (StringUtil.isBlank(menuId)) {
            return null;
        }

        ReadLock readLock = readWriteLock.readLock();
        readLock.lock();

        try {

            menu = this.allMenusMapCache.get(publicNoInfoService.getDefaultAppId()).get(menuId);
            // menu = this.menusMap.get(menuId);

        } finally {
            readLock.unlock();
        }

        return menu;

    }

    @Override
    public boolean containsEventKey(String eventKey) {

        ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            return this.allReplysCache.get(publicNoInfoService.getDefaultAppId()).containsKey(eventKey);
        } finally {
            readLock.unlock();
        }
    }

}

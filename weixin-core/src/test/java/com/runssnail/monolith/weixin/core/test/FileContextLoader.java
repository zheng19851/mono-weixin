package com.runssnail.monolith.weixin.core.test;

import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

/**
 * spring��Դ�ļ�������
 * 
 * @author tao.wangt
 * @version $Id: FileContextLoader.java,v 0.1 2010-3-18 ����10:47:14 tao.wangt Exp $
 */
public class FileContextLoader extends GenericXmlContextLoader {

    // file urlǰ׺
    private static final String FILE_PROTOCAL  = ResourceUtils.FILE_URL_PREFIX;
    // ����Ŀ¼
    private static final String FILE_BASE_DIR  = System.getProperty("user.dir");
    // �ļ�ϵͳ�ָ���
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    @Override
    protected String[] modifyLocations(Class<?> clazz, String... locations) {
        String[] modifiedLocations = new String[locations.length];
        for (int i = 0; i < locations.length; i++) {
            String path = locations[i];
            if (path.startsWith("/")) {
                modifiedLocations[i] = ResourceUtils.CLASSPATH_URL_PREFIX + path;
            } else if (!ResourcePatternUtils.isUrl(path)) {
                modifiedLocations[i] = FILE_PROTOCAL + FILE_BASE_DIR + FILE_SEPARATOR + getWebInfoRelativePath()
                                       + FILE_SEPARATOR + "conf" + FILE_SEPARATOR + "spring" + FILE_SEPARATOR + path;
            } else {
                modifiedLocations[i] = StringUtils.cleanPath(path);
            }
        }
        return modifiedLocations;
    }

    private String getWebInfoRelativePath() {
        return "src" + FILE_SEPARATOR + "main" + FILE_SEPARATOR + "webapp" + FILE_SEPARATOR + "WEB-INF";
    }
}

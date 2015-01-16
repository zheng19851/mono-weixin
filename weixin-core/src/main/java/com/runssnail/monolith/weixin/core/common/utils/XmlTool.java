package com.runssnail.monolith.weixin.core.common.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

import com.runssnail.monolith.lang.StringUtil;

/**
 * xml数据转成map
 * 
 * @author zhengwei
 * @date 2014-2-17
 */
public class XmlTool {

    /**
     * 转成Map
     * 
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Map<String, Object> toMap(String xml) {
        if (StringUtil.isBlank(xml)) {
            return null;
        }

        SAXReader reader = new SAXReader();
        StringReader sr = new StringReader(xml);
        Document document;
        try {
            document = reader.read(sr);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        Element root = document.getRootElement();
        return dom2Map(root);
    }

    private static Map dom2Map(Element e) {
        Map map = new HashMap();
        List list = e.elements();
        if (!list.isEmpty()) {
            for (Object object : list) {

                Element ele = (Element) object;

                if (!ele.elements().isEmpty()) {
                    Map m = dom2Map(ele);
                    if (map.containsKey(ele.getName())) {
                        List mapList = null;
                        Object obj = map.get(ele.getName());
                        if (obj instanceof ArrayList) {
                            mapList = (List) obj;
                            mapList.add(m);
                        } else {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        map.put(ele.getName(), mapList);
                    } else {
                        map.put(ele.getName(), m);
                    }
                } else {
                    if (map.containsKey(ele.getName())) {
                        List mapList = null;
                        Object obj = map.get(ele.getName());
                        if (obj instanceof ArrayList) {

                            mapList = (List) obj;
                            mapList.add(ele.getText());
                        } else {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(ele.getText());
                        }
                        map.put(ele.getName(), mapList);
                    } else {
                        map.put(ele.getName(), ele.getText());
                    }
                }
            }
        } else {
            map.put(e.getName(), e.getText());
        }

        return map;
    }

    public static String toXml(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("<xml>");

        for (Entry<String, String> entry : map.entrySet()) {
            if (StringUtil.isNotBlank(entry.getValue())) {
                sb.append("<").append(entry.getKey()).append(">");
                sb.append(entry.getValue());
                sb.append("</").append(entry.getKey()).append(">");
            }
        }

        sb.append("</xml>");
        return sb.toString();
    }

    public static void main(String[] args) throws DocumentException {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";

        Map<String, Object> map = XmlTool.toMap(xml);
        System.out.println(map);

    }
}

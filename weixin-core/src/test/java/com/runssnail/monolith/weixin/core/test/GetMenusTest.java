package com.runssnail.monolith.weixin.core.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.runssnail.monolith.weixin.client.menu.Menu;
import com.runssnail.monolith.weixin.core.message.domain.EnumEventType;

public class GetMenusTest {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream("d:/menus.txt"));
        BufferedReader bufferReader = new BufferedReader(reader);

        String menus = bufferReader.readLine();

        JSONObject jsonObj = JSONObject.fromObject(menus);

        JSONArray menusArr = jsonObj.getJSONObject("menu").getJSONArray("button");

        Iterator it = menusArr.iterator();
        List<Menu> menuList = new ArrayList<Menu>(menusArr.size());
        while (it.hasNext()) {
            JSONObject menuObj = (JSONObject) it.next();
            Menu menu = genMenu(menuObj);
            menuList.add(menu);
        }

        System.out.println(menuList);

    }

    private static Menu genMenu(JSONObject menuObj) {

        Menu menu = new Menu();
        menu.setName(menuObj.getString("name"));
        if (menuObj.has("type")) {
            String type = menuObj.getString("type");
            menu.setType(type);
            if (EnumEventType.isView(type)) {
                menu.setUrl(menuObj.getString("url"));
            } else if (EnumEventType.isClick(type)) {
                menu.setEventKey(menuObj.getString("key"));
            }
        }

        JSONArray subMenusArr = menuObj.getJSONArray("sub_button");
        if (subMenusArr.size() > 0) {
            Iterator it = subMenusArr.iterator();
            List<Menu> subMenuList = new ArrayList<Menu>(subMenusArr.size());
            while (it.hasNext()) {
                JSONObject subMenuObj = (JSONObject) it.next();
                Menu subMenu = genMenu(subMenuObj);
                subMenuList.add(subMenu);
            }
            menu.setSubMenus(subMenuList);
        }

        return menu;
    }
}

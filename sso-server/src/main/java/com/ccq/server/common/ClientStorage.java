package com.ccq.server.common;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ChengChuanQiang
 * @Description: 存储Session
 * @Date: Created in 2018/4/22 23:07
 */
public class ClientStorage {

    // 存储用户token，登录过的子系统
    // todo 后期可以使用Redis来进行实现
    private static Map<String, ArrayList<String>> map = new ConcurrentHashMap<String, ArrayList<String>>();

    public static void set(String token, String url) {

        if (!map.containsKey(token)) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(url);
            map.put(token, list);
            return;
        }
        map.get(token).add(url);
    }

    public static List<String> get(String token) {
        if (map.containsKey(token)) {
            return map.remove(token);
        }
        return null;
    }
}

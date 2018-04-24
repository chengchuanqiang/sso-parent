package com.ccq.client.common;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ChengChuanQiang
 * @Description: 存储Session
 * @Date: Created in 2018/4/22 23:07
 */
public class SessionStorage {

    // 存储Session的类
    // todo 后期可以使用Redis来进行实现
    private static Map<String, HttpSession> map = new ConcurrentHashMap<String, HttpSession>();

    public static void set(String token, HttpSession session) {
        map.put(token, session);
    }

    public static HttpSession get(String token) {
        if (map.containsKey(token)) {
            return map.remove(token);
        }
        return null;
    }
}

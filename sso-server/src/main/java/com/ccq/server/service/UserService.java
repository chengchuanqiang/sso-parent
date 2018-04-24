package com.ccq.server.service;

import com.ccq.client.domain.User;
import org.springframework.stereotype.Service;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/4/23 0:17
 */
@Service
public class UserService {

    public User find(User user){
        if ("ccq".equals(user.getUsername()) && "123".equals(user.getPassword())){
            return user;
        }
        return null;
    }
}

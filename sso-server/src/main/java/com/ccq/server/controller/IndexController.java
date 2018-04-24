package com.ccq.server.controller;

import com.ccq.client.common.AuthConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChengChuanQiang
 * @Description: 认证中心页面显示控制器
 * @Date: Created in 2018/4/23 0:08
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute(AuthConst.CLIENT_URL, request.getParameter(AuthConst.CLIENT_URL));
        return "index";
    }


    @RequestMapping("/success")
    public String success() {
        return "success";
    }

}

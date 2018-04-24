package com.ccq.system1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/4/24 20:09
 */
@Controller
public class indexController {

    @RequestMapping("/test")
    public String test(HttpServletRequest request, Model model) {
        return "test";
    }
    @RequestMapping("/success")
    public String success(HttpServletRequest request, Model model) {
        return "success";
    }
}

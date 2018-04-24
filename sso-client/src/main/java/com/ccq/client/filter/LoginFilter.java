package com.ccq.client.filter;

import com.ccq.client.common.AuthConst;
import com.ccq.client.common.SessionStorage;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: ChengChuanQiang
 * @Description: 客户端登录filter
 * @Date: Created in 2018/4/22 22:42
 */
public class LoginFilter implements Filter {

    private static Logger logger = Logger.getLogger(LoginFilter.class);

    FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("LoginFilter start......");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        // 已登录，放行
        if (session.getAttribute(AuthConst.IS_LOGIN) != null) {
            chain.doFilter(request, response);
            return;
        }

        // 从认证中心回跳的带有Token的请求，有效放行
        String token = request.getParameter(AuthConst.TOKEN);
        if (!StringUtils.isEmpty(token)) {
            session.setAttribute(AuthConst.IS_LOGIN, true);
            session.setAttribute(AuthConst.TOKEN, token);

            // 存储，用于注销
            SessionStorage.set(token, session);
            chain.doFilter(request, response);
            return;
        }

        logger.info("redirect :" + config.getInitParameter(AuthConst.LOGIN_URL) + "?" + AuthConst.CLIENT_URL + "=" + req.getRequestURL());

        // 重定向到登录页面，并附带上当前的请求地址
        resp.sendRedirect(config.getInitParameter(AuthConst.LOGIN_URL) + "?" + AuthConst.CLIENT_URL + "=" + req.getRequestURL());
    }

    public void destroy() {

    }
}

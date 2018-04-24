package com.ccq.server.filter;

import com.ccq.client.common.AuthConst;
import com.ccq.server.common.ClientStorage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: ChengChuanQiang
 * @Description: sso认证中心会话过滤
 * @Date: Created in 2018/4/22 23:52
 */
public class SessionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        //String uri = req.getRequestURI();
        String uri = req.getServletPath();

        // 注销请求，放行
        if ("/logout".equals(uri)) {
            chain.doFilter(request, response);
            return;
        }

        // 已经登录，放行
        if (session.getAttribute(AuthConst.IS_LOGIN) != null) {
            // 如果是客户端发起的登录请求，跳转回客户端，并附带token
            String clientUrl = request.getParameter(AuthConst.CLIENT_URL);
            String token = (String) session.getAttribute(AuthConst.TOKEN);
            if (clientUrl != null && !"".equals(clientUrl)) {
                // 存储，用于注销
                ClientStorage.set(token, clientUrl);
                resp.sendRedirect(clientUrl + "?" + AuthConst.TOKEN + "=" + token);
                return;
            }
            if (!"/success".equals(uri)) {
                resp.sendRedirect("/success");
                return;
            }
            chain.doFilter(request, response);
            return;
        }
        // 登录请求，放行
        if ("/index".equals(uri) || "/login".equals(uri)) {
            chain.doFilter(request, response);
            return;
        }

        // 其他请求，拦截
        resp.sendRedirect("index");

    }

    public void destroy() {

    }
}

package com.ccq.client.filter;

import com.ccq.client.common.AuthConst;
import com.ccq.client.common.SessionStorage;
import com.ccq.client.utils.HTTPUtil;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ChengChuanQiang
 * @Description: 客户端注销filter
 * @Date: Created in 2018/4/22 23:17
 */
public class LogoutFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String logoutUrl = config.getInitParameter(AuthConst.LOGOUT_URL);
        String token = (String) session.getAttribute(AuthConst.TOKEN);

        // 主动注销，即子系统主动提供的注销请求
        if ("/logout".equals(req.getServletPath())) {
            // 向认证中中心发送注销请求
            Map<String, String> params = new HashMap<String, String>();
            params.put(AuthConst.LOGOUT_REQUEST, token);
            HTTPUtil.post(logoutUrl, params);

            // 注销之后重定向
            resp.sendRedirect("test");

            // 注销本地回话
            session = SessionStorage.get(token);
            if (session != null) {
                session.invalidate();
            }
            return;
        }

        // 被动注销，即认证中心发送注销请求
        token = request.getParameter(AuthConst.LOGOUT_REQUEST);
        if (!StringUtils.isEmpty(token)) {
            session = SessionStorage.get(token);
            if (session != null) {
                session.invalidate();
            }
        }
        chain.doFilter(request, response);

    }

    public void destroy() {

    }
}

package com.lyon.Security;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.lyon.Security.logTime.now;


// 拦截未验证用户
public class userHandlerInterceptor extends HandlerInterceptorAdapter {

    // 拦截无token的用户
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("token");
//        String token = request.getHeader("access-control-request-headers");
        System.out.println("-user preH "+ token +now());
        return true;
        // 判断是否已有该用户登录的session
//        HttpSession session = request.getSession();
//        if (session.getAttribute("account") != null) {
//            return true;
//        }
        // 跳转到登录页
//        String url = "/art/login";
//        response.sendRedirect(url);
//        return false;
    }
}

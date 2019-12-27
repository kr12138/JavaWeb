package com.lyon.Security;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lyon.Security.logTime.now;

// 拦截教师不允许浏览的学生
public class questionAuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("token");
//        String token = request.getHeader("access-control-request-headers");
        System.out.println("---question preH "+ token +now());
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

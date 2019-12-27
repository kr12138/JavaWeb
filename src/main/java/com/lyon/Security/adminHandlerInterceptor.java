package com.lyon.Security;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lyon.Security.Decrypt.t;
import static com.lyon.Security.Encrypt.encrypt;
import static com.lyon.Security.logTime.now;
import static com.lyon.Security.Decrypt.decrypt;

// 拦截无管理权限的请求
public class adminHandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        Enumeration<String> names = request.getHeaderNames();
//        while(names.hasMoreElements()) {
//            String name = names.nextElement();
//            System.out.println("  "+ name +" : "+ request.getHeader(name));
//        }
        String token = request.getHeader("token");
        System.out.println(" token: " + token);
//        String token = request.getHeader("access-control-request-headers");
//        System.out.println("-admin preH "+ token +now());
//        return token.equals("admin");
        return true;
    }
}

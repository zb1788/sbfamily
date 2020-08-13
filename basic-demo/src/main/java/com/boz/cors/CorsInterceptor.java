package com.boz.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author boz
 * @date 2019/7/30
 */
@Configuration
public class CorsInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        String domain = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", domain);//预检测不能配置*
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "token");//只有headers里允许的值才能访问

        //如果是OPTIONS请求则结束请求
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        return true;
    }
}

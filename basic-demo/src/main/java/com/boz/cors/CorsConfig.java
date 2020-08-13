package com.boz.cors;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author boz
 * @date 2019/7/30
 */

public class CorsConfig extends WebMvcConfigurationSupport {

    //两种用法，1：addCorsMappings,解决跨域 2,通过拦截器CorsInterceptor实现，二选一

    @Resource
    private CorsInterceptor corsInterceptor;//用这个或者下面addCorsMappings二选一


//    @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")//设置允许跨域的路径
//                .allowedOrigins("*")//设置允许跨域请求的域名
//                .allowCredentials(true)//是否允许证书 不再默认开启
//                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")//设置允许的方法
//                .maxAge(3600);//跨域允许时间
//    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
    }
}

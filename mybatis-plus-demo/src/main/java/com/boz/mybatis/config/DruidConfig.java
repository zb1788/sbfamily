package com.boz.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author boz
 * @date 2019/8/9
 */
//@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean druidServlet(){//主要实现WEB监控的配置处理
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(),"/druid/*");//现在要进行druid监控的配置处理操作
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");//白名单
        servletRegistrationBean.addInitParameter("deny","192.168.111.111");//黑名单
        servletRegistrationBean.addInitParameter("loginUsername","admin");//用户名
        servletRegistrationBean.addInitParameter("loginPassword","admin");//密码
        servletRegistrationBean.addInitParameter("resetEnable","false");//是否可以充值数据源
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.css,/druid/*");
        return filterRegistrationBean;
    }


    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

}

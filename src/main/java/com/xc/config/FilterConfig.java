package com.xc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置过滤器(有多个过滤器需要分别配置)
 *
 * @author tangwei
 * @date 2019-04-08
 */
@Configuration
public class FilterConfig {



    /**
     * 单点登录过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<SsoFilter> ssoFilterRegistration() {
        // filter
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("SsoFilter");
        registration.setOrder(2);
        registration.addUrlPatterns("/*");
        registration.setFilter(new SsoFilter());
        //设置不需要登录久访问的接口，目前只支持以字符数组方式的url匹配
        return registration;
    }


}

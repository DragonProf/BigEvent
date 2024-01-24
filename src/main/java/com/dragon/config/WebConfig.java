package com.dragon.config;

import com.dragon.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //当前这个配置对象也要注入到ioc容器中，有专门的配置类容器注解，直接注入
public class WebConfig implements WebMvcConfigurer {

    @Autowired  //对于已经注入ioc容器中的对象，可以通过此注解直接使用对象
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录接口和注册接口不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login", "/user/register");
    }
}

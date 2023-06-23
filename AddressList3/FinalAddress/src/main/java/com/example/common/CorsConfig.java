package com.example.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//解决跨域问题
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")// 设置允许跨域请求的域名
//                .allowedOriginPatterns("*")
                .allowedHeaders("*")// 设置允许的请求头
                .allowCredentials(true)// 是否允许证书
                .allowedMethods("*")// 允许的方法
                .maxAge(3600);// 跨域允许时间
    }
}
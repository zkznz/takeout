package com.example.reggie_takeout.config;

import com.example.reggie_takeout.interceptors.LoginInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Component
//public class WebConfig implements WebMvcConfigurer {
////    @Autowired
////    private LoginInterceptors loginInterceptors;
//
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(loginInterceptors).excludePathPatterns();
////    }
//}

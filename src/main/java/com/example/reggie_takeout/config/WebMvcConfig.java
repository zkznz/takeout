package com.example.reggie_takeout.config;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

//@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//    //配置MVC消息转换器
//
//    @Override
//    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //创建消息转换器对象
//        MappingJackson2HttpMessageConverter messageConverter=new MappingJackson2HttpMessageConverter();
//        //设置对象转换器，将java转换为json
//        messageConverter.setObjectMapper(new JsonMapper());
//        converters.add(0,messageConverter);
//    }
//}

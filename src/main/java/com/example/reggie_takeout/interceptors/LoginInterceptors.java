package com.example.reggie_takeout.interceptors;

import com.example.reggie_takeout.common.BaseContext;
import com.example.reggie_takeout.entity.Employee;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LoginInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       Long empId=(Long) request.getSession().getAttribute("employee");
        BaseContext.setCurrentId(empId);
        return true;
    }
}

package com.example.reggie_takeout.filter;

import com.example.reggie_takeout.common.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String requestURI=request.getRequestURI();
        //放行的请求路径
        String [] urls=new String[]{
          "/employee/login",
          "employee/logout",
          "backend/**",
                "front/**"
        };
        boolean check=check(urls,requestURI);
        if(check){
            //放行请求
            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("employee")!=null){
            //放行请求
            filterChain.doFilter(request,response);
            return;
        }
        response.getWriter().write(JSON.toJSONString(R.error("未登录")));
    }
    public boolean check(String [] urls,String requestURI){
        for (String url : urls) {
            boolean match=PATH_MATCHER.match(url,requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }

}

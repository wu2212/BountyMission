package com.project.bountymission.interceptor;



import com.project.bountymission.utils.JwtUtil;
import com.project.bountymission.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.Enumeration;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("放行 OPTIONS 请求");
            return true;
        }
        //令牌验证
        System.out.println("登录拦截器触发！");
        String token = request.getHeader("Authorization");

        System.out.println(token);
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            System.out.println("token无法正常解析");
            response.setStatus(401);
            //不放行
            return false;
        }
    }
}

package com.peking.courseresourse.interceptor;

import com.alibaba.fastjson.JSON;
import dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.R;
import utils.UserHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class LoginInterceptor implements HandlerInterceptor {
    private StringRedisTemplate redisTemplate;

    public LoginInterceptor(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("authorization");
        System.out.println(token+"-----");
        String json = "";
        if(token == null || StringUtils.isBlank(json = redisTemplate.opsForValue().get("login:token:"+token))){
            R r = R.error("请先登录");
            String s = JSON.toJSONString(r);
            response.getOutputStream().write(s.getBytes(StandardCharsets.UTF_8));
            return false;
        }
        UserDTO userDTO = JSON.parseObject(json, UserDTO.class);
        UserHolder.saveUser(userDTO);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}

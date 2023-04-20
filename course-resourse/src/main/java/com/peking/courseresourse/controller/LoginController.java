package com.peking.courseresourse.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.peking.courseresourse.entity.UserEntity;
import com.peking.courseresourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JwtUtils;
import utils.R;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/login")
    public R login(String name,String password){
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        UserEntity user = userService.getOne(wrapper.eq("name", name));
        if (ObjectUtils.isEmpty(user)) return R.error().message("没有该账号");

        if (!user.getPassword().equals(password)) return R.error().message("密码不一致");

        // 登录成功，生成token
        String token = JwtUtils.getJwtToken(String.valueOf(user.getId()), user.getUserName());

        return R.ok().data("token",token).data("name",name);
    }


}

package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.peking.courseresourse.entity.Login;
import com.peking.courseresourse.entity.Result;
import com.peking.courseresourse.service.LoginService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {


    @Override
    public Result login(Login login) {
        if (StringUtils.isEmpty(login.getLoginName())){
            return new Result(400,"账号不能为空","");
        }
        if (StringUtils.isEmpty(login.getPassword())){
            return new Result(400,"密码不能为空","");
        }
        //通过登录名查询用户
        QueryWrapper<SecurityProperties.User> wrapper = new QueryWrapper();
        wrapper.eq("login_name", login.getLoginName());
        BaseMapper<SecurityProperties.User> userMapper;
        SecurityProperties.User uer=userMapper.selectOne(wrapper);
        //比较密码
        if (uer!=null&&uer.getPassword().equals(login.getPassword())){
            return new Result(200,"",uer);
        }
        return new Result(400,"登录失败","");
    }

}

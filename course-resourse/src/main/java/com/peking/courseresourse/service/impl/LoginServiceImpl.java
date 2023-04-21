package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.peking.courseresourse.dao.LoginDao;
import com.peking.courseresourse.entity.UserEntity;
import com.peking.courseresourse.dao.UserDao;
import com.peking.courseresourse.result.Result;
import com.peking.courseresourse.service.LoginService;
import org.springframework.stereotype.Service;
import com.peking.courseresourse.vo.LoginVo;

import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {


    private UserDao userDao;

    @Override
    public Result login(LoginDao loginDao) {
        if (StringUtils.isEmpty(loginDao.getUserName())){
            return new Result(400,"账号不能为空","");
        }
        if (StringUtils.isEmpty(loginDao.getPassword())){
            return new Result(400,"密码不能为空","");
        }
        //通过登录名查询用户
        QueryWrapper<UserEntity> wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginDao.getUserName());
        UserEntity uer=userDao.selectOne(wrapper);
        //比较密码
        if (uer!=null&&uer.getPassword().equals(loginDao.getPassword())){
            LoginVo loginVO=new LoginVo();
            loginVO.setId(uer.getId());
            //这里token直接用一个uuid
            //使用jwt的情况下，会生成一个jwt token,jwt token里会包含用户的信息
            loginVO.setToken(UUID.randomUUID().toString());
            loginVO.setUser(uer);
            return new Result(200,"",loginVO);
        }
        return new Result(401,"登录失败","");
    }

}

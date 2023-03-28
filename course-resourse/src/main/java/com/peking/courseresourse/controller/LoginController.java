package com.peking.courseresourse.controller;

import com.peking.courseresourse.dao.LoginDao;
import com.peking.courseresourse.result.Result;
import com.peking.courseresourse.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/api/login")
    @CrossOrigin       //后端跨域
    public Result login(@RequestBody LoginDao loginDao){
        return loginService.login(loginDao);
    }


}

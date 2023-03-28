package com.peking.courseresourse.service;

import com.peking.courseresourse.dao.LoginDao;
import com.peking.courseresourse.result.Result;

public interface LoginService {
    public Result login(LoginDao loginDao);
}

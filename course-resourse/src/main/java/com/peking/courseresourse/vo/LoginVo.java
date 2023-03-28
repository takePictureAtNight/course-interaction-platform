package com.peking.courseresourse.vo;
import com.peking.courseresourse.entity.UserEntity;

import java.io.Serializable;
public class LoginVo implements Serializable{
    private Integer id;
    private String token;
    private UserEntity user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

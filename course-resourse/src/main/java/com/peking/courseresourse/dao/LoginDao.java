package com.peking.courseresourse.dao;

public interface LoginDao {

    public String username = null;
    public String password = null;

    public default String getUserName() {
        return username;
    }



    public default String getPassword() {

        return password;
    }


}

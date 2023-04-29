package com.peking.courseresourse.dao;

import com.peking.courseresourse.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-23 12:46:02
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    UserEntity loginByRoleId(@Param("roleId") Integer roleId, @Param("username") String username, @Param("password") String password);

    String getRoleNameById(Integer id);
}

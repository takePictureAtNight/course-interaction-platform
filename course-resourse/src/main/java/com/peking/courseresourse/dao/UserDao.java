package com.peking.courseresourse.dao;

import com.peking.courseresourse.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仅该表中的用户可登录系统
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}

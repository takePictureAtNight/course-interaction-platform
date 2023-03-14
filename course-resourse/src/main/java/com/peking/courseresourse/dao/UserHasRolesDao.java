package com.peking.courseresourse.dao;

import com.peking.courseresourse.entity.UserHasRolesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户权限关联表
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@Mapper
public interface UserHasRolesDao extends BaseMapper<UserHasRolesEntity> {
	
}

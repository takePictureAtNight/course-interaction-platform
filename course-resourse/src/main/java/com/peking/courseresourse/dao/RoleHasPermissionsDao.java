package com.peking.courseresourse.dao;

import com.peking.courseresourse.entity.RoleHasPermissionsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色关联权限
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@Mapper
public interface RoleHasPermissionsDao extends BaseMapper<RoleHasPermissionsEntity> {
	
}

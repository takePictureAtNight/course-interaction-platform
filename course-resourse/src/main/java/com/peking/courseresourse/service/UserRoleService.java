package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageUtils;
import com.peking.courseresourse.entity.UserRoleEntity;

import java.util.Map;

/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-23 12:46:02
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


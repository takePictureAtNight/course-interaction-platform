package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageUtils;
import com.peking.courseresourse.entity.UserHasRolesEntity;

import java.util.Map;

/**
 * 用户权限关联表
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
public interface UserHasRolesService extends IService<UserHasRolesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


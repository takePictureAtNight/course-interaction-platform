package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageUtils;
import com.peking.courseresourse.entity.RoleHasPermissionsEntity;

import java.util.Map;

/**
 * 角色关联权限
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
public interface RoleHasPermissionsService extends IService<RoleHasPermissionsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


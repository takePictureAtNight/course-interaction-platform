package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peking.courseresourse.entity.RoleEntity;
import com.peking.courseresourse.entity.Tree;

import java.util.List;

/**
 * 系统角色
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
public interface RoleService extends IService<RoleEntity> {

    List<Tree> tree();
}


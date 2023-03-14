package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageUtils;
import com.peking.courseresourse.entity.UserEntity;

import java.util.Map;

/**
 * 仅该表中的用户可登录系统
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.peking.courseresourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peking.courseresourse.entity.UserEntity;

public interface UserMapper extends BaseMapper<UserEntity> {
    IPage<UserEntity> selectUserPage(Page<UserEntity> page, String keyword);
}
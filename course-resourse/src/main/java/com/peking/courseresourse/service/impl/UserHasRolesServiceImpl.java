package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.UserHasRolesDao;
import com.peking.courseresourse.entity.UserHasRolesEntity;
import com.peking.courseresourse.service.UserHasRolesService;


@Service("userHasRolesService")
public class UserHasRolesServiceImpl extends ServiceImpl<UserHasRolesDao, UserHasRolesEntity> implements UserHasRolesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserHasRolesEntity> page = this.page(
                new Query<UserHasRolesEntity>().getPage(params),
                new QueryWrapper<UserHasRolesEntity>()
        );

        return new PageUtils(page);
    }

}
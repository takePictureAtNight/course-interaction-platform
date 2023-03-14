package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.RoleHasPermissionsDao;
import com.peking.courseresourse.entity.RoleHasPermissionsEntity;
import com.peking.courseresourse.service.RoleHasPermissionsService;


@Service("roleHasPermissionsService")
public class RoleHasPermissionsServiceImpl extends ServiceImpl<RoleHasPermissionsDao, RoleHasPermissionsEntity> implements RoleHasPermissionsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoleHasPermissionsEntity> page = this.page(
                new Query<RoleHasPermissionsEntity>().getPage(params),
                new QueryWrapper<RoleHasPermissionsEntity>()
        );

        return new PageUtils(page);
    }

}
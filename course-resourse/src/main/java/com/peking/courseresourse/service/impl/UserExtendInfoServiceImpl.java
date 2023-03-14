package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.UserExtendInfoDao;
import com.peking.courseresourse.entity.UserExtendInfoEntity;
import com.peking.courseresourse.service.UserExtendInfoService;


@Service("userExtendInfoService")
public class UserExtendInfoServiceImpl extends ServiceImpl<UserExtendInfoDao, UserExtendInfoEntity> implements UserExtendInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserExtendInfoEntity> page = this.page(
                new Query<UserExtendInfoEntity>().getPage(params),
                new QueryWrapper<UserExtendInfoEntity>()
        );

        return new PageUtils(page);
    }

}
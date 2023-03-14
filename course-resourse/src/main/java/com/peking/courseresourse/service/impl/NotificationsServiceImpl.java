package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.NotificationsDao;
import com.peking.courseresourse.entity.NotificationsEntity;
import com.peking.courseresourse.service.NotificationsService;


@Service("notificationsService")
public class NotificationsServiceImpl extends ServiceImpl<NotificationsDao, NotificationsEntity> implements NotificationsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NotificationsEntity> page = this.page(
                new Query<NotificationsEntity>().getPage(params),
                new QueryWrapper<NotificationsEntity>()
        );

        return new PageUtils(page);
    }

}
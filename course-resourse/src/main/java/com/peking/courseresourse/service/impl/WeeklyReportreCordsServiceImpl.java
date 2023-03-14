package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.WeeklyReportreCordsDao;
import com.peking.courseresourse.entity.WeeklyReportreCordsEntity;
import com.peking.courseresourse.service.WeeklyReportreCordsService;


@Service("weeklyReportreCordsService")
public class WeeklyReportreCordsServiceImpl extends ServiceImpl<WeeklyReportreCordsDao, WeeklyReportreCordsEntity> implements WeeklyReportreCordsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WeeklyReportreCordsEntity> page = this.page(
                new Query<WeeklyReportreCordsEntity>().getPage(params),
                new QueryWrapper<WeeklyReportreCordsEntity>()
        );

        return new PageUtils(page);
    }

}
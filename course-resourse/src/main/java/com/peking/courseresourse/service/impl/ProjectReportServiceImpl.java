package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.ProjectReportDao;
import com.peking.courseresourse.entity.ProjectReportEntity;
import com.peking.courseresourse.service.ProjectReportService;


@Service("projectReportService")
public class ProjectReportServiceImpl extends ServiceImpl<ProjectReportDao, ProjectReportEntity> implements ProjectReportService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProjectReportEntity> page = this.page(
                new Query<ProjectReportEntity>().getPage(params),
                new QueryWrapper<ProjectReportEntity>()
        );

        return new PageUtils(page);
    }

}
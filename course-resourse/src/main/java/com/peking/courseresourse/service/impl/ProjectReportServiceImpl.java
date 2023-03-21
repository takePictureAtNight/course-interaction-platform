package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import dto.UserDTO;
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
import utils.UserHolder;


@Service("projectReportService")
public class ProjectReportServiceImpl extends ServiceImpl<ProjectReportDao, ProjectReportEntity> implements ProjectReportService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();
        LambdaQueryWrapper<ProjectReportEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//.eq(ProjectReportEntity::getCreateBy,user.getId())
                .eq(params.get("projectName") != null, ProjectReportEntity::getProjectName, params.get("projectName"))
                .eq(params.get("keywords") != null, ProjectReportEntity::getKeywords, params.get("keywords"))
                .eq(params.get("projectCategory") != null, ProjectReportEntity::getProjectCategory, params.get("projectCategory"));
        IPage<ProjectReportEntity> page = this.page(
                new Query<ProjectReportEntity>().getPage(params),
                lambdaQueryWrapper
        );

        return new PageUtils(page);
    }

}
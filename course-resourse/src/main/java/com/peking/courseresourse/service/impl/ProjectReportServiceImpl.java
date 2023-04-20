package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.ProjectReportDao;
import com.peking.courseresourse.entity.ProjectReportEntity;
import com.peking.courseresourse.service.ProjectReportService;
import utils.R;
import utils.UserHolder;


@Service("projectReportService")
public class ProjectReportServiceImpl extends ServiceImpl<ProjectReportDao, ProjectReportEntity> implements ProjectReportService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();
        //项目报告搜索条件
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

    @Override
    @Transactional
    public R updateStatus(Integer id, String status, String returnReason) {
        LambdaUpdateWrapper<ProjectReportEntity> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(ProjectReportEntity::getId, id);//作为条件
        updateWrapper.set(ProjectReportEntity::getStatus, status);//设置想要更新的字段
        updateWrapper.set(ProjectReportEntity::getReturnReason, returnReason);//设置想要更新的字段


        //这里的实体类设置为空
        if (!update(null, updateWrapper)) {
            return R.error("修改失败");
        }
        return R.ok();
    }

}
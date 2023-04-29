package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.entity.ProductDesignEntity;
import com.peking.courseresourse.vo.UpdateStatusVo;
import dto.UserDTO;
import org.apache.commons.lang.StringUtils;
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
                .eq(params.get("projectCategory") != null, ProjectReportEntity::getProjectCategory, params.get("projectCategory"))
                .eq(ProjectReportEntity::getStatus,1);
        IPage<ProjectReportEntity> page = this.page(
                new Query<ProjectReportEntity>().getPage(params),
                lambdaQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public R updateStatus(UpdateStatusVo updateStatusVo) {
        UpdateWrapper<ProjectReportEntity> wrapper = new UpdateWrapper<ProjectReportEntity>().eq("id", updateStatusVo.getId());
        Integer status = updateStatusVo.getStatus();
        String returnReason = updateStatusVo.getReturnReason();
        wrapper.set(status != null,"status",status);
        wrapper.set(StringUtils.isNotBlank(returnReason),"return_reason",returnReason);
        this.update(wrapper);
        return R.ok();
    }

    @Override
    public R saveAll(ProjectReportEntity projectReport) {
        Integer id = UserHolder.getUser().getId();
        //保存后状态为为审核0
       projectReport.setStatus(0);
        //保存用户id 创建人  //需登录完成后 现在是假的
        projectReport.setCreateBy(id);
        this.save(projectReport);
        return R.ok();
    }

}
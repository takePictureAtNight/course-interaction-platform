package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.entity.ProductDesignEntity;
import com.peking.courseresourse.entity.ProjectReportEntity;
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

import com.peking.courseresourse.dao.WeeklyReportreCordsDao;
import com.peking.courseresourse.entity.WeeklyReportreCordsEntity;
import com.peking.courseresourse.service.WeeklyReportreCordsService;
import utils.R;
import utils.UserHolder;


@Service("weeklyReportreCordsService")
public class WeeklyReportreCordsServiceImpl extends ServiceImpl<WeeklyReportreCordsDao, WeeklyReportreCordsEntity> implements WeeklyReportreCordsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();
        //周报记录搜索条件
        LambdaQueryWrapper<WeeklyReportreCordsEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//.eq(WeeklyReportreCordsEntity::getCreateBy,user.getId())
                .eq(params.get("internshipCommunity") != null,WeeklyReportreCordsEntity::getInternshipCommunity, params.get("internshipCommunity"))
                .ge(params.get("internshipBegintime") != null, WeeklyReportreCordsEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .le(params.get("internshipEndtime") != null, WeeklyReportreCordsEntity::getInternshipEndtime, params.get("internshipEndtime"))
                .eq(params.get("weeklyreportName") != null, WeeklyReportreCordsEntity::getWeeklyreportName, params.get("weeklyreportName"))
                .eq(params.get("keywords") != null, WeeklyReportreCordsEntity::getKeywords, params.get("keywords"))
                .like(params.get("serviceTarget") != null, WeeklyReportreCordsEntity::getServiceTarget, params.get("serviceTarget"))
                .eq(WeeklyReportreCordsEntity::getStatus,1);
        IPage<WeeklyReportreCordsEntity> page = this.page(
                new Query<WeeklyReportreCordsEntity>().getPage(params),
                lambdaQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public R updateStatus(UpdateStatusVo updateStatusVo) {
        UpdateWrapper<WeeklyReportreCordsEntity> wrapper = new UpdateWrapper<WeeklyReportreCordsEntity>().eq("id", updateStatusVo.getId());
        Integer status = updateStatusVo.getStatus();
        String returnReason = updateStatusVo.getReturnReason();
        wrapper.set(status != null,"status",status);
        wrapper.set(StringUtils.isNotBlank(returnReason),"return_reason",returnReason);
        this.update(wrapper);
        return R.ok();
    }

    @Override
    public R saveAll(WeeklyReportreCordsEntity weeklyReportreCords) {
        Integer id = UserHolder.getUser().getId();
        //保存后状态为为审核0
        weeklyReportreCords.setStatus(0);
        //保存用户id 创建人
        weeklyReportreCords.setCreateBy(id);
        this.save(weeklyReportreCords);
        return R.ok();
    }

}
package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import dto.UserDTO;
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
import utils.UserHolder;


@Service("weeklyReportreCordsService")
public class WeeklyReportreCordsServiceImpl extends ServiceImpl<WeeklyReportreCordsDao, WeeklyReportreCordsEntity> implements WeeklyReportreCordsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();
        //周报记录搜索条件
        LambdaQueryWrapper<WeeklyReportreCordsEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//.eq(WeeklyReportreCordsEntity::getCreateBy,user.getId())
                .eq(WeeklyReportreCordsEntity::getInternshipCommunity, params.get("internshipCommunity"))
                .ge(params.get("internshipBegintime") != null, WeeklyReportreCordsEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .le(params.get("internshipEndtime") != null, WeeklyReportreCordsEntity::getInternshipEndtime, params.get("internshipEndtime"))
                .eq(params.get("weeklyreportName") != null, WeeklyReportreCordsEntity::getWeeklyreportName, params.get("weeklyreportName"))
                .eq(params.get("keywords") != null, WeeklyReportreCordsEntity::getKeywords, params.get("keywords"))
                .like(params.get("serviceTarget") != null, WeeklyReportreCordsEntity::getServiceTarget, params.get("serviceTarget"));
        IPage<WeeklyReportreCordsEntity> page = this.page(
                new Query<WeeklyReportreCordsEntity>().getPage(params),
                lambdaQueryWrapper
        );

        return new PageUtils(page);
    }

}
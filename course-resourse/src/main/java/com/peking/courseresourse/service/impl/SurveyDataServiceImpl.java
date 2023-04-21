package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.SurveyDataDao;
import com.peking.courseresourse.entity.SurveyDataEntity;
import com.peking.courseresourse.service.SurveyDataService;


@Service("surveyDataService")
public class SurveyDataServiceImpl extends ServiceImpl<SurveyDataDao, SurveyDataEntity> implements SurveyDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SurveyDataEntity> page = this.page(
                new Query<SurveyDataEntity>().getPage(params),
                new QueryWrapper<SurveyDataEntity>()
        );

        return new PageUtils(page);
    }

}
package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peking.courseresourse.dao.SurveyDataListDao;
import com.peking.courseresourse.dto.SurveyDataListDTO;
import com.peking.courseresourse.entity.SurveyDataEntity;
import com.peking.courseresourse.service.SurveyDataListService;
import com.peking.courseresourse.service.SurveyDataService;
import org.springframework.stereotype.Service;
import utils.PageUtils;
import utils.Query;

import java.util.Map;


@Service("surveyDataListService")
public class SurveyDataListServiceImpl extends ServiceImpl<SurveyDataListDao, SurveyDataListDTO> implements SurveyDataListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SurveyDataListDTO> page = this.page(
                new Query<SurveyDataListDTO>().getPage(params),
                new QueryWrapper<SurveyDataListDTO>()
        );

        return new PageUtils(page);
    }

}
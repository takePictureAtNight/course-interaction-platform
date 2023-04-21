package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageUtils;
import com.peking.courseresourse.entity.SurveyDataEntity;

import java.util.Map;

/**
 * 调研数据表
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-21 10:21:44
 */
public interface SurveyDataService extends IService<SurveyDataEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


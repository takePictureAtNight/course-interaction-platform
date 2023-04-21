package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peking.courseresourse.dto.SurveyDataListDTO;
import com.peking.courseresourse.entity.SurveyDataEntity;
import utils.PageUtils;

import java.util.Map;

/**
 * 调研数据表
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-21 10:21:44
 */
public interface SurveyDataListService extends IService<SurveyDataListDTO> {

    PageUtils queryPage(Map<String, Object> params);
}


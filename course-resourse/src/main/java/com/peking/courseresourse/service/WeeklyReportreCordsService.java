package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageUtils;
import com.peking.courseresourse.entity.WeeklyReportreCordsEntity;

import java.util.Map;

/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
public interface WeeklyReportreCordsService extends IService<WeeklyReportreCordsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


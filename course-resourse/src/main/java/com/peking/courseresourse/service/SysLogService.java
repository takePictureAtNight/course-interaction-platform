package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageUtils;
import com.peking.courseresourse.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-15 10:52:12
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


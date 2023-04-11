package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.PageUtils;
import com.peking.courseresourse.entity.NoticeEntity;

import java.util.Map;

/**
 * 通知公告表
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-11 14:19:01
 */
public interface NoticeService extends IService<NoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.NoticeDao;
import com.peking.courseresourse.entity.NoticeEntity;
import com.peking.courseresourse.service.NoticeService;


@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, NoticeEntity> implements NoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NoticeEntity> page = this.page(
                new Query<NoticeEntity>().getPage(params),
                new QueryWrapper<NoticeEntity>()
        );

        return new PageUtils(page);
    }

}
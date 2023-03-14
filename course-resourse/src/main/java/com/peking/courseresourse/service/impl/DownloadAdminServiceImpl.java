package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.DownloadAdminDao;
import com.peking.courseresourse.entity.DownloadAdminEntity;
import com.peking.courseresourse.service.DownloadAdminService;


@Service("downloadAdminService")
public class DownloadAdminServiceImpl extends ServiceImpl<DownloadAdminDao, DownloadAdminEntity> implements DownloadAdminService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DownloadAdminEntity> page = this.page(
                new Query<DownloadAdminEntity>().getPage(params),
                new QueryWrapper<DownloadAdminEntity>()
        );

        return new PageUtils(page);
    }

}
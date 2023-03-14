package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.CaseTableDao;
import com.peking.courseresourse.entity.CaseTableEntity;
import com.peking.courseresourse.service.CaseTableService;


@Service("caseTableService")
public class CaseTableServiceImpl extends ServiceImpl<CaseTableDao, CaseTableEntity> implements CaseTableService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CaseTableEntity> page = this.page(
                new Query<CaseTableEntity>().getPage(params),
                new QueryWrapper<CaseTableEntity>()
        );

        return new PageUtils(page);
    }

}
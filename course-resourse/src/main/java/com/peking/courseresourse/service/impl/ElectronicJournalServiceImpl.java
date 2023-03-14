package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.ElectronicJournalDao;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.service.ElectronicJournalService;


@Service("electronicJournalService")
public class ElectronicJournalServiceImpl extends ServiceImpl<ElectronicJournalDao, ElectronicJournalEntity> implements ElectronicJournalService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ElectronicJournalEntity> page = this.page(
                new Query<ElectronicJournalEntity>().getPage(params),
                new QueryWrapper<ElectronicJournalEntity>()
        );

        return new PageUtils(page);
    }

}
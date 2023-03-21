package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peking.courseresourse.entity.CaseTableEntity;
import dto.UserDTO;
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
import utils.UserHolder;


@Service("electronicJournalService")
public class ElectronicJournalServiceImpl extends ServiceImpl<ElectronicJournalDao, ElectronicJournalEntity> implements ElectronicJournalService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();

        LambdaQueryWrapper<ElectronicJournalEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//.eq(ElectronicJournalEntity::getCreateBy,user.getId())
                .eq(ElectronicJournalEntity::getInternshipCommunity, params.get("internshipCommunity"))
                .ge(params.get("internshipBegintime") != null, ElectronicJournalEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .le(params.get("internshipEndtime") != null, ElectronicJournalEntity::getInternshipBegintime, params.get("internshipEndtime"))
                .like(params.get("title") != null, ElectronicJournalEntity::getTitle, params.get("title"))
                .eq(params.get("keywords") != null, ElectronicJournalEntity::getKeywords, params.get("keywords"))
                .like(params.get("serviceTarget") != null, ElectronicJournalEntity::getServiceTarget, params.get("serviceTarget"))
                .eq(ElectronicJournalEntity::getType, params.get("type"));
        IPage<ElectronicJournalEntity> page = this.page(
                new Query<ElectronicJournalEntity>().getPage(params),
                lambdaQueryWrapper
        );

        return new PageUtils(page);
    }

}
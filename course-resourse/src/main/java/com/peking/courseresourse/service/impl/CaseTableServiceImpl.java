package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import dto.UserDTO;
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
import utils.UserHolder;


@Service("caseTableService")
public class CaseTableServiceImpl extends ServiceImpl<CaseTableDao, CaseTableEntity> implements CaseTableService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();

        LambdaQueryWrapper<CaseTableEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//eq(CaseTableEntity::getCreateBy, user.getId())
                .eq(CaseTableEntity::getInternshipCommunity, params.get("internshipCommunity"))
                .ge(params.get("internshipBegintime") != null, CaseTableEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .le(params.get("internshipBegintime") != null, CaseTableEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .like(params.get("caseName") != null, CaseTableEntity::getCaseName, params.get("caseName"))
                .eq(params.get("keywords") != null, CaseTableEntity::getKeywords, params.get("keywords"))
                .like(params.get("serviceTarget") != null, CaseTableEntity::getServiceTarget, params.get("serviceTarget"))
                .eq(CaseTableEntity::getCaseType, params.get("caseType"))
                .eq(CaseTableEntity::getType, params.get("type"));
        IPage<CaseTableEntity> page = this.page(
                new Query<CaseTableEntity>().getPage(params),
                lambdaQueryWrapper
        );
        return new PageUtils(page);
    }

}
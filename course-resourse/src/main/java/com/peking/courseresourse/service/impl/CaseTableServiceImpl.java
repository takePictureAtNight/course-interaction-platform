package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import dto.CaseTableDTO;
import dto.UploadDTO;
import dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.multipart.MultipartFile;
import utils.FileUtils;
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

        //典型案例和小组活动案例搜索条件
        LambdaQueryWrapper<CaseTableEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//eq(CaseTableEntity::getCreateBy, user.getId())
                .eq(CaseTableEntity::getInternshipCommunity, params.get("internshipCommunity"))
                .ge(params.get("internshipBegintime") != null, CaseTableEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .le(params.get("internshipEndtime") != null, CaseTableEntity::getInternshipEndtime, params.get("internshipEndtime"))
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

    @Override
    public void saveAll(CaseTableDTO caseTable) {
        MultipartFile[] files = caseTable.getFiles();
        List<UploadDTO> list = FileUtils.upload(files);
        List<CaseTableEntity> caseTableEntityList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CaseTableEntity caseTable1 = new CaseTableEntity();
            BeanUtils.copyProperties(caseTable,caseTable1);
            UploadDTO uploadDTO = list.get(i);
            caseTable1.setFileName(uploadDTO.getOriginalFilename());
            caseTable1.setResourceUrl(uploadDTO.getResourceUrl());
            caseTableEntityList.add(caseTable1);
        }
        this.saveBatch(caseTableEntityList);
    }

}
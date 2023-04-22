package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.peking.courseresourse.service.FileService;
import dto.CaseTableDTO;
import dto.UploadDTO;
import dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private FileService fileService;
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
    public void saveAll(CaseTableEntity caseTable) {
        //保存后状态为为审核0
        caseTable.setStatus("0");
        //保存用户id 创建人  //需登录完成后 现在是假的
        caseTable.setCreateBy(12);
        this.save(caseTable);
    }

}
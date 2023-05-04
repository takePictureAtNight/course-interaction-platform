package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.service.FileService;
import com.peking.courseresourse.vo.UpdateStatusVo;
import dto.CaseTableDTO;
import dto.UploadDTO;
import dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import utils.*;

import com.peking.courseresourse.dao.CaseTableDao;
import com.peking.courseresourse.entity.CaseTableEntity;
import com.peking.courseresourse.service.CaseTableService;


@Service("caseTableService")
public class CaseTableServiceImpl extends ServiceImpl<CaseTableDao, CaseTableEntity> implements CaseTableService {
    @Autowired
    private FileService fileService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();
        //实习社区：（下拉框中显示所有社区，包括：承泽园，畅春园，中关园，燕北园，燕园街道，朗润园，蔚秀园，燕东园）
        //实习时间：（输入年月日，显示当天的所有典型个案）
        //案例名称：
        //关键词：
        //服务对象：（模糊搜索）
        //案例类型：（下拉框中显示所有案例类型，包括：供给与需求评估，服务与活动策划，服务与活动执行，服务评估与改进建议，其他社区办公室安排的工作）
        //典型案例和小组活动案例搜索条件
        LambdaQueryWrapper<CaseTableEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//eq(CaseTableEntity::getCreateBy, user.getId())
                .eq(params.get("internshipCommunity") != null,CaseTableEntity::getInternshipCommunity, params.get("internshipCommunity"))
                .ge(params.get("internshipBegintime") != null, CaseTableEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .le(params.get("internshipEndtime") != null, CaseTableEntity::getInternshipEndtime, params.get("internshipEndtime"))
                .like(params.get("caseName") != null, CaseTableEntity::getCaseName, params.get("caseName"))
                .eq(params.get("keywords") != null, CaseTableEntity::getKeywords, params.get("keywords"))
                .like(params.get("serviceTarget") != null, CaseTableEntity::getServiceTarget, params.get("serviceTarget"))
                //.eq(CaseTableEntity::getCaseType, params.get("caseType"))
                .eq(params.get("type") != null,CaseTableEntity::getType, params.get("type"))
                .eq(CaseTableEntity::getStatus,1);
        IPage<CaseTableEntity> page = this.page(
                new Query<CaseTableEntity>().getPage(params),
                lambdaQueryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public void saveAll(CaseTableEntity caseTable) {
        Integer id = UserHolder.getUser().getId();
        //保存后状态为为审核0
        caseTable.setStatus(0);
        //保存用户id 创建人  //需登录完成后 现在是假的
        caseTable.setCreateBy(id);
        this.save(caseTable);
    }

    @Override
    public R updateStatus(UpdateStatusVo updateStatusVo) {
        UpdateWrapper<CaseTableEntity> wrapper = new UpdateWrapper<CaseTableEntity>().eq("id", updateStatusVo.getId());
        Integer status = updateStatusVo.getStatus();
        String returnReason = updateStatusVo.getReturnReason();
        wrapper.set(status != null, "status", status);
        wrapper.set(StringUtils.isNotBlank(returnReason), "return_reason", returnReason);
        this.update(wrapper);
        return R.ok();
    }

    @Transactional
    public R updateStatus(Integer id, String status, String returnReason) {
        LambdaUpdateWrapper<CaseTableEntity> updateWrapper = new LambdaUpdateWrapper<>();


        updateWrapper.eq(CaseTableEntity::getId, id);//作为条件
        updateWrapper.set(CaseTableEntity::getStatus, status);//设置想要更新的字段
        updateWrapper.set(CaseTableEntity::getReturnReason, returnReason);//设置想要更新的字段

        //这里的实体类设置为空
        if (!update(null, updateWrapper)) {
            return R.error("修改失败");
        }
        return R.ok();
    }

}
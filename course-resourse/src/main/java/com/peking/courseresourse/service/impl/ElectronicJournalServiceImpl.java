package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peking.courseresourse.dao.ElectronicJournalDao;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.service.ElectronicJournalService;
import dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.PageUtils;
import utils.Query;
import utils.R;
import utils.UserHolder;

import java.util.Map;


@Service("electronicJournalService")
public class ElectronicJournalServiceImpl extends ServiceImpl<ElectronicJournalDao, ElectronicJournalEntity> implements ElectronicJournalService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        UserDTO user = UserHolder.getUser();
        //个人和小组电子期刊搜索条件
        LambdaQueryWrapper<ElectronicJournalEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper//.eq(ElectronicJournalEntity::getCreateBy,user.getId())
                .eq(ElectronicJournalEntity::getInternshipCommunity, params.get("internshipCommunity"))
                .ge(params.get("internshipBegintime") != null, ElectronicJournalEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .le(params.get("internshipEndtime") != null, ElectronicJournalEntity::getInternshipEndtime, params.get("internshipEndtime"))
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

    @Override
    @Transactional
    public R updateStatus(Integer id, String status, String returnReason) {
        LambdaUpdateWrapper<ElectronicJournalEntity> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(ElectronicJournalEntity::getId, id);//作为条件
        updateWrapper.set(ElectronicJournalEntity::getStatus, status);//设置想要更新的字段
        updateWrapper.set(ElectronicJournalEntity::getReturnReason, returnReason);//设置想要更新的字段


        //这里的实体类设置为空
        if (!update(null, updateWrapper)) {
            return R.error("修改失败");
        }
        return R.ok();
    }

}
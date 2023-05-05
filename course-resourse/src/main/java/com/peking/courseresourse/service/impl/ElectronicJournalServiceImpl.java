package com.peking.courseresourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peking.courseresourse.dao.ElectronicJournalDao;
import com.peking.courseresourse.entity.CaseTableEntity;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.service.ElectronicJournalService;
import com.peking.courseresourse.vo.UpdateStatusVo;
import dto.UserDTO;
import org.apache.commons.lang.StringUtils;
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
                .eq(params.get("internshipCommunity") != null,ElectronicJournalEntity::getInternshipCommunity, params.get("internshipCommunity"))
                .ge(params.get("internshipBegintime") != null, ElectronicJournalEntity::getInternshipBegintime, params.get("internshipBegintime"))
                .le(params.get("internshipEndtime") != null, ElectronicJournalEntity::getInternshipEndtime, params.get("internshipEndtime"))
                .like(params.get("title") != null, ElectronicJournalEntity::getTitle, params.get("title"))
                .eq(params.get("keywords") != null, ElectronicJournalEntity::getKeywords, params.get("keywords"))
                .like(params.get("serviceTarget") != null, ElectronicJournalEntity::getServiceTarget, params.get("serviceTarget"))
                .eq(params.get("type") != null,ElectronicJournalEntity::getType, params.get("type"))
                .eq(ElectronicJournalEntity::getStatus,1);
        IPage<ElectronicJournalEntity> page = this.page(
                new Query<ElectronicJournalEntity>().getPage(params),
                lambdaQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public R updateStatus(UpdateStatusVo updateStatusVo) {
        UpdateWrapper<ElectronicJournalEntity> wrapper = new UpdateWrapper<ElectronicJournalEntity>().eq("id", updateStatusVo.getId());
        Integer status = updateStatusVo.getStatus();
        String returnReason = updateStatusVo.getReturnReason();
        wrapper.set(status != null,"status",status);
        wrapper.set(StringUtils.isNotBlank(returnReason),"return_reason",returnReason);
        this.update(wrapper);
        return R.ok();
    }

    @Override
    public R saveAll(ElectronicJournalEntity electronicJournal) {
        Integer id = UserHolder.getUser().getId();
        //保存后状态为为审核0
        electronicJournal.setStatus(0);
        //保存用户id 创建人  //需登录完成后 现在是假的
        electronicJournal.setCreateBy(id);
        this.save(electronicJournal);
        return R.ok();
    }

}
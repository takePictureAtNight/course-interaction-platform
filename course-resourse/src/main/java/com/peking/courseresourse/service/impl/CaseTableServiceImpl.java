package com.peking.courseresourse.service.impl;

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
        QueryWrapper<CaseTableEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("create_by", user.getId());
        //模糊查询拼接wrapper
         String internshipCommunity = (String) params.get("internshipCommunity");
        if(internshipCommunity!=null){
            wrapper.eq("internship_community",internshipCommunity);
        }
        String internshipBegintime = (String) params.get("internshipBegintime");
        if(internshipBegintime!=null){
            wrapper.eq("internship_begintime",internshipBegintime);
        }
       //......
        IPage<CaseTableEntity> page = this.page(
                new Query<CaseTableEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

}
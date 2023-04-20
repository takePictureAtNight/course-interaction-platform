//package com.peking.courseresourse.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.service.IService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.peking.courseresourse.dao.CaseTableDao;
//import com.peking.courseresourse.entity.CaseTableEntity;
//import com.peking.courseresourse.entity.ElectronicJournalEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import utils.R;
//
///**
// * @ClassName UpdateStatus
// * @Description TODO
// * @Author JFeng
// * @Date 2023/4/20 16:25
// * @Version 1.0
// */
//@Service
//public class UpdateStatus<T> extends ServiceImpl implements IService<T> {
//
//
//    @Autowired
//    private CaseTableServiceImpl caseTableService;
//    @Autowired
//    private CaseTableDao caseTableDao;
//
//
//    void F(Integer id, String status, String returnReason) {
//
//
//        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
//
//        updateWrapper.eq("id", id);//作为条件
//
//        updateWrapper.set("status", status);//设置想要更新的字段
//
//        updateWrapper.set("returnReason", returnReason);
//
//        update(null, updateWrapper);//这里的实体类设置为空
//    }
//}

package com.peking.courseresourse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peking.courseresourse.dao.CaseTableDao;
import com.peking.courseresourse.entity.CaseTableEntity;
import com.peking.courseresourse.entity.UserEntity;
import com.peking.courseresourse.service.CaseTableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CourseResourseApplicationTests {
    @Autowired
    private CaseTableDao caseTableDao;
    @Test
    void contextLoads() {
    }
    @Test
    void test() {
        CaseTableEntity caseTableEntity = new CaseTableEntity();
        QueryWrapper<CaseTableEntity> wrapper = new QueryWrapper<>();
        Page<CaseTableEntity> caseTableEntityPage = caseTableDao.selectPage(new Page<>(1, 10), null);
        List<CaseTableEntity> records = caseTableEntityPage.getRecords();
        long total = caseTableEntityPage.getTotal();
        System.out.println(total);

    }
    @Test
    void test1(){
        List<UserEntity> list = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        list.add(userEntity);
        System.out.println(userEntity);
    }

}

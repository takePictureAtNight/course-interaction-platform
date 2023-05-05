package com.peking.courseresourse;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peking.courseresourse.dao.CaseTableDao;
import com.peking.courseresourse.dao.UserDao;
import com.peking.courseresourse.entity.CaseTableEntity;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.entity.UserEntity;
import com.peking.courseresourse.service.CaseTableService;
import com.peking.courseresourse.service.ElectronicJournalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CourseResourseApplicationTests {
    @Autowired
    private CaseTableDao caseTableDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ElectronicJournalService electronicJournalService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    void contextLoads() {
    }
    @Test
    void test() {
    }
    @Test
    void test1(){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort((o1, o2) -> o1 >o2?o2:o1);
        System.out.println(list);
//        UserEntity user = userDao.loginByRoleId(1, "root", "root");
//        System.out.println(user);
    }


}

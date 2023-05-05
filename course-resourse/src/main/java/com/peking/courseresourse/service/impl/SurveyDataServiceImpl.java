package com.peking.courseresourse.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import utils.PageUtils;
import utils.Query;

import com.peking.courseresourse.dao.SurveyDataDao;
import com.peking.courseresourse.entity.SurveyDataEntity;
import com.peking.courseresourse.service.SurveyDataService;


@Service("surveyDataService")
public class SurveyDataServiceImpl extends ServiceImpl<SurveyDataDao, SurveyDataEntity> implements SurveyDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SurveyDataEntity> page = this.page(
                new Query<SurveyDataEntity>().getPage(params),
                new QueryWrapper<SurveyDataEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getGender() {
        QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("case a1 when 1 then '男' when 2 then '女' end name, count(*) as value").isNotNull("a1").groupBy("a1");
        List<Map<String, Object>> list = listMaps(queryWrapper);
        return list;
    }

    @Override
    public List<Map<String, Object>> getBirth() {
        QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("a5year as name, count(*) as value").isNotNull("a5year").groupBy("a5year");
        List<Map<String, Object>> list = listMaps(queryWrapper);
        return list;
    }

    @Override
    public List<Map<String, Object>> getEdu() {
        QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("case a6 when 1 then '不识字' when 2 then '未读完小学，但能读写' when 3 then '小学（包括私塾）' when 4 then '初中' when 5 then '高中/中专/职高/技校' when 6 then '大专/高职/夜校' when 7 then '本科及以上' end name, count(*) as value").isNotNull("a6").groupBy("a6");
        List<Map<String, Object>> list = listMaps(queryWrapper);
        return list;
    }

    @Override
    public List<Map<String, Object>> getMarriage() {
        QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("case b1 when 1 then '有配偶' when 2 then '丧偶' when 3 then '离婚' when 4 then '从未结婚' end name, count(*) as value").isNotNull("b1").groupBy("b1");
        List<Map<String, Object>> list = listMaps(queryWrapper);
        return list;
    }

    @Override
    public List<Map<String, Object>> getCohabit() {
        String[] col = {"b41", "b42", "b43", "b44", "b45", "b46", "b47"};
        String[] name = {"单独居住", "配偶/伴侣", "(岳) 父母", "儿子/儿媳", "女儿/女婿", "（外、重）孙子女", "保姆"};
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < col.length; i++) {
            QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("case " + col[i] + " when 1 then '" + name[i] + "' end name, count(*) as value").eq(col[i], 1);
            Map<String, Object> map = getMap(queryWrapper);
            list.add(map);
        }
        QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("b48", 2).ne("b48", "");
        long count = count(queryWrapper);
        list.add(new HashMap<String, Object>() {{
            put("name", "其他");
            put("value", count);
        }});
        return list;
    }

    @Override
    public List<Map<String, Object>> getIncome() {
        QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("case c2 when 1 then '0-999元' when 2 then '1000-2999元' when 3 then '3000-5999元' when 4 then '6000-9999元' when 5 then '10000元及以上' end name, count(*) as value").isNotNull("c2").groupBy("c2");
        List<Map<String, Object>> list = listMaps(queryWrapper);
        return list;
    }

    @Override
    public List<Map<String, Object>> getDisease() {
        String[] col = {"d11", "d12", "d13", "d14", "d15", "d16", "d17", "d18", "d19", "d11"};
        String[] name = {"白内障/青光眼", "高血压", "糖尿病", "消化系统疾病", "心脑血管疾病", "骨关节病", "呼吸系统疾病", "恶性肿瘤", "生殖系统疾病", "无慢性病"};
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < col.length; i++) {
            QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("case " + col[i] + " when 1 then '" + name[i] + "' end name, count(*) as value").eq(col[i], 1);
            Map<String, Object> map = getMap(queryWrapper);
            list.add(map);
        }
        QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("d110", 2).ne("d110", "");
        long count = count(queryWrapper);
        list.add(new HashMap<String, Object>() {{
            put("name", "其他慢性病");
            put("value", count);
        }});
        return list;
    }

    @Override
    public List<Map<String, Object>> getService() {
        String[] row = {"助餐服务", "家政服务", "医疗康复护理服务", "老年辅具用品服务", "居住环境改造服务", "心理咨询服务", "文体活动及课程服务", "紧急救援"};
        String[] col = {"社区是否有", "是否体验过", "是否满意", "是否需要"};
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 1; i <= row.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            List<Map<String, Object>> list1 = new ArrayList<>();
            map.put("name", row[i - 1]);
            for (int j = 1; j <= col.length; j++) {
                HashMap<String, Object> map1 = new HashMap<>();
                map1.put("name", col[j - 1]);
                QueryWrapper<SurveyDataEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("case e0" + (i) + j + " when 1 then '是' when 2 then '否' when 3 then '一般' end name, count(*) as value").isNotNull("e0" + (i) + j).groupBy("e0" + (i) + j);
                List<Map<String, Object>> list2 = listMaps(queryWrapper);
                map1.put("value", list2);
                list1.add(map1);
            }
            map.put("value", list1);
            list.add(map);
        }
        return list;
    }
}
package com.peking.courseresourse.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.peking.courseresourse.service.SurveyDataListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peking.courseresourse.entity.SurveyDataEntity;
import com.peking.courseresourse.service.SurveyDataService;
import utils.PageUtils;
import utils.R;


/**
 * 调研数据表
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-21 10:21:44
 */
@RestController
@RequestMapping("courseresourse/surveydata")
public class SurveyDataController {
    @Autowired
    private SurveyDataService surveyDataService;

    @Autowired
    private SurveyDataListService listService;

    /**
     * 答卷列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = listService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 答卷信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        SurveyDataEntity surveyData = surveyDataService.getById(id);
        return R.ok().put("surveyData", surveyData);
    }

    /**
     * 保存答卷
     */
    @PostMapping("/save")
    public R save(@RequestBody SurveyDataEntity surveyData) {
        surveyData.setCreateTime(new Date());
        surveyDataService.save(surveyData);
        return R.ok();
    }

    /**
     * 修改答卷
     */
    @PostMapping("/update")
    public R update(@RequestBody SurveyDataEntity surveyData) {
        surveyDataService.updateById(surveyData);
        return R.ok();
    }

    /**
     * 删除答卷
     */
    @GetMapping("/delete")
    public R delete(Integer id) {
        surveyDataService.removeById(id);
        return R.ok();
    }

    /**
     * 统计性别
     */
    @GetMapping("/gender")
    public R getGender() {
        List<Map<String, Object>> gender = surveyDataService.getGender();
        return R.ok().put("gender", gender);
    }

    /**
     * 统计年龄
     */
    @GetMapping("/birth")
    public R getBirth() {
        List<Map<String, Object>> birth = surveyDataService.getBirth();
        return R.ok().put("birth", birth);
    }

    /**
     * 统计文化程度
     */
    @GetMapping("/edu")
    public R getEdu() {
        List<Map<String, Object>> edu = surveyDataService.getEdu();
        return R.ok().put("edu", edu);
    }

    /**
     * 统计婚姻状况
     */
    @GetMapping("/marriage")
    public R getMarriage() {
        List<Map<String, Object>> marriage = surveyDataService.getMarriage();
        return R.ok().put("marriage", marriage);
    }

    /**
     * 统计同住
     */
    @GetMapping("/cohabit")
    public R getCohabit() {
        List<Map<String, Object>> cohabit = surveyDataService.getCohabit();
        return R.ok().put("cohabit", cohabit);
    }

    /**
     * 统计收入
     */
    @GetMapping("/income")
    public R getIncome() {
        List<Map<String, Object>> income = surveyDataService.getIncome();
        return R.ok().put("income", income);
    }

    /**
     * 统计慢性病
     */
    @GetMapping("/disease")
    public R getDisease() {
        List<Map<String, Object>> disease = surveyDataService.getDisease();
        return R.ok().put("disease", disease);
    }

    /**
     * 统计社区服务
     */
    @GetMapping("/service")
    public R getService() {
        List<Map<String, Object>> service = surveyDataService.getService();
        return R.ok().put("service", service);
    }
}

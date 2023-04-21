package com.peking.courseresourse.controller;

import java.util.Arrays;
import java.util.Date;
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
     * 统计分析
     */
}

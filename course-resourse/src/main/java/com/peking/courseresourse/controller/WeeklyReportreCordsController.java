package com.peking.courseresourse.controller;

import java.util.Arrays;
import java.util.Map;

import com.peking.courseresourse.vo.UpdateStatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.peking.courseresourse.entity.WeeklyReportreCordsEntity;
import com.peking.courseresourse.service.WeeklyReportreCordsService;
import utils.PageUtils;
import utils.R;



/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
@Api(tags = "周报记录接口")
@RestController
@RequestMapping("courseresourse/weeklyreportrecords")
public class WeeklyReportreCordsController {
    @Autowired
    private WeeklyReportreCordsService weeklyReportreCordsService;

    /**
     * 列表
     */
    @ApiOperation("多条件查询")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = weeklyReportreCordsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		WeeklyReportreCordsEntity weeklyReportreCords = weeklyReportreCordsService.getById(id);

        return R.ok().put("weeklyReportreCords", weeklyReportreCords);
    }

    /**
     * 保存
     */
    @ApiOperation("上传保存")
    @PostMapping("/save")
    public R save(@RequestBody WeeklyReportreCordsEntity weeklyReportreCords){
		return weeklyReportreCordsService.saveAll(weeklyReportreCords);

    }

    /**
     * 修改
     */
    @ApiOperation("审核案例库上传")
    @PostMapping("/updateStatus")
    public R update(UpdateStatusVo updateStatusVo) {
        //修改产品报告的审核状态
        return weeklyReportreCordsService.updateStatus(updateStatusVo);

    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete( Integer id){
		weeklyReportreCordsService.removeById(id);
        return R.ok();
    }

}

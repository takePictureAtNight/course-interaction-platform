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
import com.peking.courseresourse.entity.ProjectReportEntity;
import com.peking.courseresourse.service.ProjectReportService;
import utils.PageUtils;
import utils.R;



/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
@Api(tags = "项目报告接口")
@RestController
@RequestMapping("courseresourse/projectreport")
public class ProjectReportController {
    @Autowired
    private ProjectReportService projectReportService;

    /**
     * 列表
     */
    @ApiOperation("多条件查询")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectReportService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{int}")
    public R info(@PathVariable("int") Integer id){
		ProjectReportEntity projectReport = projectReportService.getById(id);

        return R.ok().put("projectReport", projectReport);
    }

    /**
     * 保存
     */
    @ApiOperation("上传保存")
    @PostMapping("/save")
    public R save(@RequestBody ProjectReportEntity projectReport){
		return projectReportService.saveAll(projectReport);
    }

    /**
     * 修改
     */
    @ApiOperation("审核案例库上传")
    @PostMapping("/updateStatus")
    public R update(@RequestBody UpdateStatusVo updateStatusVo) {
        //修改项目报告的审核状态
        return projectReportService.updateStatus(updateStatusVo);

    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public R delete( Integer id){
		projectReportService.removeById(id);
        return R.ok();
    }

}

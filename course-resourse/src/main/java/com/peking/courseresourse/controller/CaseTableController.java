package com.peking.courseresourse.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import annotation.SysLog;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.peking.courseresourse.entity.*;
import com.peking.courseresourse.service.*;
import com.peking.courseresourse.vo.UpdateStatusVo;
import dto.CaseTableDTO;
import dto.UploadDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import utils.FileUtils;
import utils.PageUtils;
import utils.R;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
@Api(tags = "案例库接口")
@RestController
@RequestMapping("courseresourse/casetable")
public class CaseTableController {
    @Autowired
    private CaseTableService caseTableService;
//    @Autowired
//    private ElectronicJournalService electronicJournalService;
//    @Autowired
//    private WeeklyReportreCordsService reportreCordsService;
//    @Autowired
//    private ProductDesignService designService;
//    @Autowired
//    private ProjectReportService projectReportService;

    /**
     * 列表(用户所有)
     */
    @ApiOperation("多条件查询")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = caseTableService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息  我的资源  根据id查询
     *
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        CaseTableEntity caseTable = caseTableService.getById(id);
        caseTableService.list(new QueryWrapper<>(caseTable).eq("create_by",id));
        return R.ok().put("data", caseTable);
    }
    @ApiOperation("审核案例库上传")
    @PostMapping("/updateStatus")
    public R updateStatus(@RequestBody UpdateStatusVo updateStatusVo){
        return caseTableService.updateStatus(updateStatusVo);
    }

    /**
     * 保存
     */
    @ApiOperation("上传保存")
    @PostMapping("/save")
    public R save(@RequestBody CaseTableEntity caseTable) {
        caseTableService.saveAll(caseTable);
        return R.ok("保存成功");
    }

    /**
     * 修改
     */
//    @PostMapping("/updateStatus")
//    public R update(@RequestParam Integer id, @RequestParam String status, @RequestParam String returnReason) {
//        //修改案例的审核状态
//        return caseTableService.updateStatus(id, status, returnReason);
//    }

    /**
     * 删除
     */
    @SysLog("删除")
    @GetMapping("/delete")
    public R delete(Integer id) {
        caseTableService.removeById(id);
        return R.ok();
    }

}

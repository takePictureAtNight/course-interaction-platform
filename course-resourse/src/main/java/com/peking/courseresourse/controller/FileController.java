package com.peking.courseresourse.controller;


import annotation.SysLog;
import cn.hutool.core.io.FileUtil;
import com.peking.courseresourse.entity.*;
import com.peking.courseresourse.service.*;
import exception.RException;
import dto.UploadDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import utils.FileUtils;
import utils.PageUtils;
import utils.R;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
@Api(tags = "文件上传下载接口")
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private CaseTableService caseTableService;
    @Autowired
    private ElectronicJournalService electronicJournalService;
    @Autowired
    private WeeklyReportreCordsService reportreCordsService;
    @Autowired
    private ProductDesignService designService;
    @Autowired
    private ProjectReportService projectReportService;
    @Autowired
    private FileService fileService;
    //模版上传
    //@SysLog("模版上传")
    @ApiOperation("文件上传(保存到数据库)")
    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        return fileService.upload(file);
    }
    @ApiOperation("其他5个上传通用文件上传")
    @PostMapping("/other/upload")
    public R otherUpload(MultipartFile file){
        return fileService.otherUpload(file);
    }
    //分页参数
    @ApiOperation("获取所有文件(模版)")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page =  fileService.queryPage(params);
        return R.ok().put("data", page);
    }
    @ApiOperation("根据id删除")
    @GetMapping("/delete")
    public R delete(Integer id){
        fileService.removeById(id);
        return R.ok();
    }
    //模版下载
    @ApiOperation("文件下载")
    @GetMapping("/download")
    public R download(Integer id, HttpServletResponse response){
        return fileService.download(id,response);
    }

    //通用下载接口
    /**
     //     * 通用下载接口
     //     *
     //     * @param id       上传文件的id
     //     * @param type     案例库,电子期刊  规定 案例库为0 电子期刊为1 周报记录2 产品设计 3 项目报告4
     //     * @param response
     //     * @return
     //     * @throws IOException
     //     */
//    @GetMapping("/download")
//    public R download(Integer id, Integer type, HttpServletResponse response) throws IOException {
//        String resourceUrl = "";
//        switch (type) {
//            case 0:
//                CaseTableEntity caseTable = caseTableService.getById(id);
//                resourceUrl = caseTable.getResourceUrl();
//                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(caseTable.getFileName(), "UTF-8"));
//                break;
//            case 1:
//                ElectronicJournalEntity journalEntity = electronicJournalService.getById(id);
//                resourceUrl = journalEntity.getResourceUrl();
//                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(journalEntity.getFileName(), "UTF-8"));
//                break;
//            case 2:
//                WeeklyReportreCordsEntity reportEntity = reportreCordsService.getById(id);
//                resourceUrl = reportEntity.getResourceUrl();
//                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(reportEntity.getFileName(), "UTF-8"));
//                break;
//            case 3:
//                ProductDesignEntity designEntity = designService.getById(id);
//                resourceUrl = designEntity.getResourceUrl();
//                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(designEntity.getFileName(), "UTF-8"));
//                break;
//            case 4:
//                ProjectReportEntity projectReportEntity = projectReportService.getById(id);
//                resourceUrl = projectReportEntity.getResourceUrl();
//                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(projectReportEntity.getFileName(), "UTF-8"));
//                break;
//            default:
//                return R.error("文件下载失败");
//        }
//        byte[] bytes = FileUtil.readBytes(resourceUrl);
//        OutputStream outputStream = response.getOutputStream();
//        outputStream.write(bytes);
//        outputStream.flush();
//        outputStream.close();
//        return R.ok("下载成功");
//    }
}

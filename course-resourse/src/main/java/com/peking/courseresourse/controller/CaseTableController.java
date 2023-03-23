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
import com.peking.courseresourse.entity.*;
import com.peking.courseresourse.service.*;
import dto.CaseTableDTO;
import dto.UploadDTO;
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
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = caseTableService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        CaseTableEntity caseTable = caseTableService.getById(id);

        return R.ok().put("caseTable", caseTable);
    }


//    /**
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
//
//    /**
//     * @param caseTableDTO 前端传过来的dto
//     * @return
//     * @throws IOException
//     */
//
//    @PostMapping("/upload")
//    public R upload(CaseTableDTO caseTableDTO) throws IOException {
//        MultipartFile[] files = caseTableDTO.getFiles();
//        if (files == null || files.length == 0) {
//            return R.error("上传文件不能为空");
//        }
//        for (MultipartFile file : files) {
//            String originalFilename = file.getOriginalFilename();
//            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
//            System.out.println(extension);
//            if (!".doc".equals("." + extension) && !".docx".equals("." + extension)) {
//                return R.error("文件格式错误无法上传");
//            }
//            String newFilename = new SimpleDateFormat("yyyMMddHHmmss").format(new Date()) + UUID.randomUUID() + "." + extension;
//            String path = ResourceUtils.getURL("classpath:").getPath() + "static/files";
//            System.out.println(path);
//            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            String dateDirPath = path + "/" + format;
//            File datePath = new File(dateDirPath);
//            if (!datePath.exists()) {
//                datePath.mkdirs();
//            }
//            try {
//                file.transferTo(new File(datePath, newFilename));
//                CaseTableEntity caseTableEntity = new CaseTableEntity();
//                BeanUtils.copyProperties(caseTableDTO, caseTableEntity);
//                caseTableEntity.setResourceUrl(path + "/" + format + "/" + newFilename);
//                caseTableEntity.setFileName(originalFilename);
//                caseTableService.save(caseTableEntity);
//            } catch (Exception e) {
//                R.error("上传失败");
//            }
//        }
//        return R.ok("上传成功");
//    }
//
//    /**
//     * 保存
//     */
//    @PostMapping("/save")
//    public R save(CaseTableDTO caseTable) {
//        caseTableService.saveAll(caseTable);
//        return R.ok();
//    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody CaseTableEntity caseTable) {
        caseTableService.updateById(caseTable);

        return R.ok();
    }

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

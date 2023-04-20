package com.peking.courseresourse.controller;


import cn.hutool.core.io.FileUtil;
import com.peking.courseresourse.entity.*;
import com.peking.courseresourse.service.*;
import exception.RException;
import dto.UploadDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import utils.FileUtils;
import utils.R;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @PostMapping("/upload")
    public R upload(MultipartFile[] files) throws Exception {
        List<UploadDTO> upload = fileService.upload(files);
        return R.ok("上传成功").put("list",upload);
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
    @GetMapping("/download")
    public R download(Integer id, Integer type, HttpServletResponse response) throws IOException {
        String resourceUrl = "";
        switch (type) {
            case 0:
                CaseTableEntity caseTable = caseTableService.getById(id);
                resourceUrl = caseTable.getResourceUrl();
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(caseTable.getFileName(), "UTF-8"));
                break;
            case 1:
                ElectronicJournalEntity journalEntity = electronicJournalService.getById(id);
                resourceUrl = journalEntity.getResourceUrl();
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(journalEntity.getFileName(), "UTF-8"));
                break;
            case 2:
                WeeklyReportreCordsEntity reportEntity = reportreCordsService.getById(id);
                resourceUrl = reportEntity.getResourceUrl();
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(reportEntity.getFileName(), "UTF-8"));
                break;
            case 3:
                ProductDesignEntity designEntity = designService.getById(id);
                resourceUrl = designEntity.getResourceUrl();
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(designEntity.getFileName(), "UTF-8"));
                break;
            case 4:
                ProjectReportEntity projectReportEntity = projectReportService.getById(id);
                resourceUrl = projectReportEntity.getResourceUrl();
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(projectReportEntity.getFileName(), "UTF-8"));
                break;
            default:
                return R.error("文件下载失败");
        }
        byte[] bytes = FileUtil.readBytes(resourceUrl);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
        return R.ok("下载成功");
    }
}

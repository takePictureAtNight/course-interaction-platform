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

    @PostMapping("/upload")
    public R upload(MultipartFile[] files) throws Exception {
        if (files == null || files.length == 0) {
            throw new RException("上传文件不能为空");
//            return R.error("上传文件不能为空");
        }
        //返回数组
        List<UploadDTO> list = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            System.out.println(extension);
            if (!".doc".equals("." + extension) && !".docx".equals("." + extension)) {
                throw new RException("文件格式错误无法上传");
            }
            String newFilename = new SimpleDateFormat("yyyMMddHHmmss").format(new Date()) + UUID.randomUUID() + "." + extension;
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/files";
            System.out.println(path);
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String dateDirPath = path + "/" + format;
            File datePath = new File(dateDirPath);
            if (!datePath.exists()) {
                datePath.mkdirs();
            }
            try {
                file.transferTo(new File(datePath, newFilename));
                UploadDTO u = new UploadDTO();
                String resourceUrl = path + "/" + format + "/" + newFilename;
                u.setResourceUrl(resourceUrl);
                u.setOriginalFilename(originalFilename);
                list.add(u);
            } catch (Exception e) {
                throw new RException("上传失败");
            }
        }
        return R.ok("上传成功").put("data",list);
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

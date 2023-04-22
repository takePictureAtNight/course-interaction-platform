package com.peking.courseresourse.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.peking.courseresourse.dao.ElectronicJournalDao;
import com.peking.courseresourse.dao.FilesDao;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.entity.FilesEntity;
import com.peking.courseresourse.entity.UserInfoEntity;
import com.peking.courseresourse.service.FileService;
import dto.UploadDTO;
import exception.RException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import utils.PageUtils;
import utils.Query;
import utils.R;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("fileService")
public class FileServiceImpl extends ServiceImpl<FilesDao, FilesEntity> implements FileService {
    @Override
    public R upload(MultipartFile file) {
        UploadDTO uploadDTO = commonUpload(file);
        FilesEntity filesEntity = new FilesEntity();
            filesEntity.setFileName(uploadDTO.getOriginalFilename());
            filesEntity.setUploadTime(new Date());
            filesEntity.setResourceUrl(uploadDTO.getResourceUrl());
            filesEntity.setUserId(10);
            save(filesEntity);
        return R.ok("上传成功");
    }
    public UploadDTO commonUpload(MultipartFile file){
        if (file == null) {
            throw new RException("上传文件不能为空");
//            return R.error("上传文件不能为空");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        System.out.println(extension);
        if (!".doc".equals("." + extension) && !".docx".equals("." + extension)) {
            throw new RException("文件格式错误无法上传");
        }
        String newFilename = new SimpleDateFormat("yyyMMddHHmmss").format(new Date()) + UUID.randomUUID() + "." + extension;
        String path = null;
        try {
            path = ResourceUtils.getURL("classpath:").getPath() + "static/files";
        } catch (FileNotFoundException e) {
            throw new RException("找不到文件");
        }
        System.out.println(path);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath = path + "/" + format;
        File datePath = new File(dateDirPath);
        if (!datePath.exists()) {
            datePath.mkdirs();
        }

        UploadDTO u = new UploadDTO();
        String resourceUrl = "/files/" + format + "/" + newFilename;
        try {
            file.transferTo(new File(datePath, newFilename));

            u.setResourceUrl(resourceUrl);
            u.setOriginalFilename(originalFilename);
        } catch (Exception e) {
            throw new RException("上传失败");
        }
        return u;
    }
    @Override
    public R download(Integer id, HttpServletResponse response) {
        FilesEntity file = this.getById(id);
        String resourceUrl = file.getResourceUrl();
        String path;
        try {
           path  = ResourceUtils.getURL("classpath:").getPath()+"static"+resourceUrl;
        } catch (FileNotFoundException e) {
            return R.error();
        }
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return R.error("下载失败");
        }
        byte[] bytes = FileUtil.readBytes(path);
        OutputStream outputStream ;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            return R.error("下载失败");
        }

        return R.ok("下载成功");
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FilesEntity> page = this.page(
                new Query<FilesEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public R otherUpload(MultipartFile file) {
        UploadDTO uploadDTO = commonUpload(file);
        return R.ok("上传成功").put("data",uploadDTO);
    }
}

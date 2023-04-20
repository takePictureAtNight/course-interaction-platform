package com.peking.courseresourse.service.impl;

import cn.hutool.core.util.IdUtil;
import com.peking.courseresourse.service.FileService;
import dto.UploadDTO;
import exception.RException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("fileService")
public class FileServiceImpl implements FileService {
    @Override
    public List<UploadDTO> upload(MultipartFile[] files) {
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
            String newFilename = IdUtil.simpleUUID() + "." + extension;
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
            try {
                file.transferTo(new File(datePath, newFilename));
                UploadDTO u = new UploadDTO();
                String resourceUrl = "/files/" + format + "/" + newFilename;
                u.setResourceUrl(resourceUrl);
                u.setOriginalFilename(originalFilename);
                list.add(u);
            } catch (Exception e) {
                throw new RException("上传失败");
            }
        }
        return list;
    }
}

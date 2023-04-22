package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import com.peking.courseresourse.entity.FilesEntity;
import dto.UploadDTO;
import org.springframework.web.multipart.MultipartFile;
import utils.PageUtils;
import utils.R;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface FileService extends IService<FilesEntity> {

     R upload(MultipartFile file);

    R download(Integer id, HttpServletResponse response);

    PageUtils queryPage(Map<String, Object> params);

    R otherUpload(MultipartFile file);
}

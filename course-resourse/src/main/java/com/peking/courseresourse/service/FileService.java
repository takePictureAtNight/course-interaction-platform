package com.peking.courseresourse.service;

import dto.UploadDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileService {

    List<UploadDTO> upload(MultipartFile[] files);
}

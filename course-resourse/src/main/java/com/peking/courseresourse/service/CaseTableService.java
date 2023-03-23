package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dto.CaseTableDTO;
import org.springframework.web.multipart.MultipartFile;
import utils.PageUtils;
import com.peking.courseresourse.entity.CaseTableEntity;

import java.util.Map;

/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
public interface CaseTableService extends IService<CaseTableEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAll(CaseTableDTO caseTableDTO);
}


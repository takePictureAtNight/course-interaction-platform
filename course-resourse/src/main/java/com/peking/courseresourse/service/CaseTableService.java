package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peking.courseresourse.vo.UpdateStatusVo;
import dto.CaseTableDTO;
import org.springframework.web.multipart.MultipartFile;
import utils.PageUtils;
import com.peking.courseresourse.entity.CaseTableEntity;
import utils.R;

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

    void saveAll(CaseTableEntity caseTableDTO);

    R updateStatus(UpdateStatusVo updateStatusVo);
}


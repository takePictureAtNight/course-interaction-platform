package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peking.courseresourse.vo.UpdateStatusVo;
import utils.PageUtils;
import com.peking.courseresourse.entity.ElectronicJournalEntity;
import utils.R;

import java.util.Map;

/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
public interface ElectronicJournalService extends IService<ElectronicJournalEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R updateStatus(UpdateStatusVo updateStatusVo);

    R saveAll(ElectronicJournalEntity electronicJournal);
}


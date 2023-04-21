package com.peking.courseresourse.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.peking.courseresourse.dto.SurveyDataListDTO;
import com.peking.courseresourse.entity.SurveyDataEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 调研数据表
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-21 10:21:44
 */
@Mapper
public interface SurveyDataListDao extends BaseMapper<SurveyDataListDTO> {
	
}

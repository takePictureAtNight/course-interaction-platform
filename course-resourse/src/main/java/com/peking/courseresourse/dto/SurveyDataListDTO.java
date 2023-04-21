package com.peking.courseresourse.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 调研数据表
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-21 10:21:44
 */
@Data
@TableName("survey_data")
public class SurveyDataListDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Date createTime;

}

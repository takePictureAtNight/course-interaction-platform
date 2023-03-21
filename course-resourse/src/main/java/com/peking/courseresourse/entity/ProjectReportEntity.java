package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
@Data
@TableName("tab_project_report")
public class ProjectReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 关键词
	 */
	private String keywords;
	/**
	 * 项目类别
	 */
	private String projectCategory;
	/**
	 * 指导老师
	 */
	private String instructor;
	/**
	 * 上传资料日期
	 */
	private Date uploadresourceDate;
	/**
	 * 上传资源文件url
	 */
	private String resourceUrl;
	/**
	 * 1代表通过审核，0代表待审核
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer createBy;
	/*
	 * 文件名称
	 * */
	private String fileName;
}

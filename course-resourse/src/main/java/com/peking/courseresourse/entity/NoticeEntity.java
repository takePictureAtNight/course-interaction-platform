package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 通知公告表
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-11 14:19:01
 */
@Data
@TableName("notice")
public class NoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 公告ID
	 */
	@TableId
	private Integer id;
	/**
	 * 公告标题 
	 */
	private String title;
	/**
	 * 公告内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}

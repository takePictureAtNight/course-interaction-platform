package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-04-23 12:46:02
 */
@Data
@TableName("tab_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId(type = IdType.AUTO)
	private Integer userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 学号/教师号
	 */
	private String number;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 00 系统用户 01 注册用户
	 */
	private String userType;
	/**
	 * 0代表未审核 1代表审核成功 2代表审核失败
	 */
	private Integer status;
	//角色id(调整为1对多)
	private Integer roleId;

}

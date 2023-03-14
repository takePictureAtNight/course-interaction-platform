package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户权限关联表
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@Data
@TableName("rbac_user_has_roles")
public class UserHasRolesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * user_info表id
	 */
	private Integer userId;
	/**
	 * rbac_role表id
	 */
	private Integer roleId;
	/**
	 * 
	 */
	private Date createdAt;

}

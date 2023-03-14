package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色关联权限
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@Data
@TableName("rbac_role_has_permissions")
public class RoleHasPermissionsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 权限ID
	 */
	private Integer permissionId;
	/**
	 * 
	 */
	private Date createdAt;

}

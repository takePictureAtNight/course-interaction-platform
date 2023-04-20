package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@Data
@TableName("rbac_permission")
public class PermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private Integer pId;

	private String path;

	private String href;

	private String icon;

	private String name;

	// -1:目录,0:菜单,1:按钮
	private Integer isMenu;

	private String target;

	private Integer state;

	@TableField(exist = false)
	private List<PermissionEntity> children;

	@TableLogic
	private Integer isDeleted;

	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModifeld;

}

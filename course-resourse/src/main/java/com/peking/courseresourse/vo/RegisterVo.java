package com.peking.courseresourse.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("注册vo")
public class RegisterVo {
    @ApiModelProperty("角色id")
    private Integer roleId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("姓名")
    private String realName;
    @ApiModelProperty("学号/教师号")
    private String number;
    @ApiModelProperty("电话号码")
    private String phone;
}

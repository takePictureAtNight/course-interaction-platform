package com.peking.courseresourse.vo;
import com.peking.courseresourse.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel("登录vo")
public class LoginVo implements Serializable{

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("获取验证码返回的uuid(/getCode)")
    private String uuid;
    @ApiModelProperty("角色id 1:超级管理员 2:管理员 3:教师 4:学生")
    private Integer roleId;
}

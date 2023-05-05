package com.peking.courseresourse.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel("5种上传审核vo(案例库...)")
@Data//@RequestParam("id")Integer id,@RequestParam("status") Integer status
public class UpdateStatusVo {
    @ApiModelProperty("id")
    private Integer id;  //课程资源id
    @ApiModelProperty("修改的状态")
    private Integer status; //修改的状态
    @ApiModelProperty("审核失败 退回原因")
    private String returnReason; //退回的原因
}

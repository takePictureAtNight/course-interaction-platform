package com.peking.courseresourse.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("我的资源返回实体")
public class ReturnResource {
    @ApiModelProperty("资源名称")
    private String fileName;//资源名称
    @ApiModelProperty("上传时间")
    private Date  uploadresourceDate; // 上传时间
    @ApiModelProperty("实习社区")
    private String internshipCommunity; //实习社区
    @ApiModelProperty("审核状态 0 未审核 1 审核成功 2 审核失败")
    private Integer status; //状态
    @ApiModelProperty("退回原因 状态为2时才有值")
    private String returnReason;
}

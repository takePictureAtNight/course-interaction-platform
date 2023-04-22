package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
@TableName("tab_files")
public class FilesEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String fileName;
    private String resourceUrl;
    //2023-04-22
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date  uploadTime;
    private Integer userId;
}

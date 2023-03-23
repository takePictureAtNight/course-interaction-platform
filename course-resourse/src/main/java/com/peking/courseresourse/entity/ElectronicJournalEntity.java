package com.peking.courseresourse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:49:11
 */
@Data
@TableName("tab_electronic_journal")
public class ElectronicJournalEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 实习社区
     */
    private String internshipCommunity;
    /**
     * 实习开始时间
     */
    private Date internshipBegintime;
    /**
     * 实习结束时间
     */
    private Date internshipEndtime;
    /**
     * 工作地点
     */
    private String workLocation;
    /**
     * 篇名
     */
    private String title;
    /**
     * 关键词
     */
    private String keywords;
    /**
     * 服务对象
     */
    private String serviceTarget;
    /**
     * 上传资源日期
     */
    private Date uploadresourceDate;
    /**
     * 上传资源文件url
     */
    private String resourceUrl;
    /**
     * 1代表个人电子期刊，2代表小组电子期刊
     */
    private Integer type;
    /**
     * 电子期刊上传者id
     */
    private Integer createBy;
    /**
     * 1代表通过审核，0代表待审核
     */
    private String status;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 逻辑删除，未删除未0，已删除未1
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(select = false)
    private int deleteFlag;
}

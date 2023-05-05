package com.peking.courseresourse.controller;

import com.peking.courseresourse.service.ResourceService;
import com.peking.courseresourse.service.impl.ResourceServiceImpl;
import dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.R;
import utils.UserHolder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/resource")
@Api(tags = "课程资源(只有我的资源和饼状图 剩下在其他controller)")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    //我的资源
    @ApiOperation("我的资源list 获取全部上传的课程资源(包括审核失败和待审核)")
    @GetMapping("/me")
    public R listById(){
        UserDTO user = UserHolder.getUser();
       return resourceService.me(user.getId());
    }
    //资源审核doc预览
    @ApiOperation("doc预览 type 1 案例库 2电子期刊 3产品设计 4项目报告 5 周报记录")
    @GetMapping("/preview")
    public void preview(Integer id, Integer type, HttpServletResponse response){
       resourceService.preview(id,type,response);
    }
    @ApiOperation("获取第一个饼状图数据(各课程资源数量)")
    @GetMapping("/pie")
    public R getPie(){
        return resourceService.getPie();
    }
    @ApiOperation("获取第二个饼状图数据(各社区课程资源数量)")
    @GetMapping("/community/pie")
    public R getComPie(){
        return resourceService.getComPie();
    }
}

package com.peking.courseresourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.peking.courseresourse.entity.PermissionEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface PermissionService extends IService<PermissionEntity> {

    Map<String, Object> toTree(HttpServletRequest request);


    List<PermissionEntity> treeSelect();


    void removeMenu(Integer id);
}



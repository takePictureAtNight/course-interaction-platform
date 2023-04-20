package com.peking.courseresourse.controller;

import annotation.Authority;
import authority.UnifyAuthorityVerify;
import com.peking.courseresourse.entity.PermissionEntity;
import com.peking.courseresourse.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.R;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author yy
 * @email 3110311633@qq.com
 * @date 2023-03-14 20:50:46
 */
@RestController
@RequestMapping("courseresourse/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 权限列表
     * @return
     */
    @GetMapping("/list")
    @Authority(value = "permission:list",verify = UnifyAuthorityVerify.class)
    public List<PermissionEntity> list(){
        return permissionService.list();
    }


    /**
     * 新增权限时树形结构
     * @return
     */
    @GetMapping("/treeSelect")
    @Authority("permission:treeSelect")
    public List<PermissionEntity> treeSelect(){
        List<PermissionEntity> data = permissionService.treeSelect();

        return data;
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @PostMapping("/add")
    @Authority("permission:add")
    public R add(@RequestBody PermissionEntity permission){
        permission.setIcon("fa "+permission.getIcon());
        permissionService.save(permission);

        return R.ok();
    }

    /**
     * 修改权限
     * @param permission
     * @return
     */
    @PutMapping("/update")
    @Authority("permission:update")
    public R update(@RequestBody PermissionEntity permission){
        permission.setIcon("fa "+permission.getIcon());
        permissionService.updateById(permission);
        return R.ok();

    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @Authority("permission:delete")
    public R delete(@PathVariable Integer id){
        permissionService.removeMenu(id);
        return R.ok().message("删除成功");
    }


    /**
     * 初始化菜单
     * @return
     */
    @GetMapping("/initMenu")
    public Map<String,Object> initMenu(HttpServletRequest request){
        Map<String,Object> data = permissionService.toTree(request);
        return data;
    }

}
